/**
 * @file mosaiccanvas.h
 */

#include <iostream>
#include <sys/stat.h>
#include <errno.h>
#include <cstdlib>

#include "mosaiccanvas.h"
#include "util/util.h"

using namespace std;
using namespace util;

bool MosaicCanvas::enableOutput = false;

/**
 * Constructor.
 *
 * @param theRows Number of rows to divide the canvas into
 * @param theColumns Number of columns to divide the canvas into
 */
MosaicCanvas::MosaicCanvas(int theRows, int theColumns)
    : rows(theRows), columns(theColumns)
{
    if ((theRows < 1) || (theColumns < 1)) {
        cerr << "Error: Cannot set non-positive rows or columns" << endl;
        exit(-1);
    }

    myIndex.resize(rows*columns);
}

/**
 * Retrieve the number of rows of images
 *
 * @return The number or rows in the mosaic, or -1 on error
 */
int MosaicCanvas::getRows() const
{
    return rows;
}

/**
 * Retrieve the number of columns of images
 *
 * @return The number of columns in the mosaic, or -1 or error
 */
int MosaicCanvas::getColumns() const
{
    return columns;
}
void MosaicCanvas::setImage(unsigned size, vector<TileImage> const&tile){
  for(unsigned i = 0; i < size ; i++){
    myImages.push_back(tile[i]);
  }
}
void MosaicCanvas::resizeImage(int pixelsPerTile){

    int width = columns * pixelsPerTile;
    int height = rows * pixelsPerTile;
    // Create list of drawable tiles
    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < columns; col++) {
            int startX = divide(width  * col,       getColumns());
            int endX   = divide(width  * (col + 1), getColumns());
            int startY = divide(height * row,       getRows());
            int endY   = divide(height * (row + 1), getRows());

            myImages[myIndex[row * columns + col]].setPixel(startX, startY, endX - startX);
        }
    }
}
void MosaicCanvas::setTile(int row, int column, unsigned i)
{
  if (enableOutput) {
      cerr << "\rPopulating Mosaic: setting tile ("
           << row << ", " << column
           << ")" << string(20, ' ') << "\r";
      cerr.flush();
  }
  myIndex[row*columns+column] = i;
}

const TileImage& MosaicCanvas::getTile(int row, int column) const
{
    return myImages[myIndex[row * columns + column]];
}

PNG MosaicCanvas::drawMosaic(int pixelsPerTile) const
{
    if (pixelsPerTile <= 0) {
        cerr << "ERROR: pixelsPerTile must be > 0" << endl;
        exit(-1);
    }

    int width = columns * pixelsPerTile;
    int height = rows * pixelsPerTile;

    // Create the image
    PNG mosaic(width, height);

    // Create list of drawable tiles
    for (int row = 0; row < rows; row++) {
        if (enableOutput) {
            cerr << "\rDrawing Mosaic: resizing tiles ("
                 << (row * columns + /*col*/ 0 + 1) << "/" << (rows * columns)
                 << ")" << string(20, ' ') << "\r";
            cerr.flush();
        }
        for (int col = 0; col < columns; col++) {
            int startX = divide(width  * col,       getColumns());
            int endX   = divide(width  * (col + 1), getColumns());
            int startY = divide(height * row,       getRows());
            int endY   = divide(height * (row + 1), getRows());

            if (endX - startX != endY - startY)
                cerr << "Error: resolution not constant: x: " << (endX - startX)
                     << " y: " << (endY - startY) << endl;
            myImages[myIndex[row * columns + col]].paste(mosaic, startX, startY, endX - startX);
        }
    }
    if (enableOutput) {
        cerr << "\r" << string(60, ' ');
        cerr << "\rDrawing Mosaic: resizing tiles ("
             << (rows * columns) << "/" << (rows * columns) << ")" << endl;
        cerr.flush();
    }

    return mosaic;
}
