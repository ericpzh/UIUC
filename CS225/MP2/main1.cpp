
#include "StickerSheet.h"
#include <iostream>
#include "assert.h"

using namespace cs225;

int main() {
  /*Image alma, I, Sticker1, Sticker2, Sticker3, Sticker4;

  alma.readFromFile("alma.png");
  I.readFromFile("i.png");
  Sticker1.readFromFile("sticker1.png");
  Sticker1.scale(0.4);
  Sticker2.readFromFile("Sticker2.png");
  Sticker2.scale(0.7);
  Sticker3.readFromFile("Sticker3.png");
  Sticker3.scale(0.3);
  StickerSheet sheet(alma, 5);


  sheet.addSticker(Sticker1, 515, 55);
  sheet.addSticker(Sticker2, 375, 3);
  sheet.addSticker(Sticker3, 300, 56);
  alma = sheet.render();
  alma.writeToFile("23y.png");*/


  Image alma, I, Sticker1, Sticker2, Sticker3, Sticker4;

  alma.readFromFile("alma.png");
  I.readFromFile("i.png");

  StickerSheet sheet(alma, 5);
  StickerSheet sheet1(sheet);

  //(sheet.render()).writeToFile("output0.png");
  //(sheet1.render()).writeToFile("output0.png");
  for(int index = -3; index < 15; index++)
  {
    sheet1.removeSticker(index);
    Image* Test1 = sheet1.getSticker(index);
    Image* Test2 = sheet.getSticker(index);
    assert(Test1 == Test2);
    assert(sheet.translate(index, 100, 100) == 0);
  }

  for(int index = -3; index < 15; index++)
  {
    sheet.removeSticker(index);
    Image* Test = sheet.getSticker(index);
    assert(Test == NULL);
    assert(sheet.translate(index, 100, 100) == 0);
  }
  assert(sheet.render() == alma);

  assert(sheet.addSticker(I, 20, 100) == 0);
  assert(sheet.addSticker(I, 100, 200) == 1);
  assert(sheet.addSticker(I, 100, 20) == 2);

  //(sheet.render()).writeToFile("output1.png");
  assert(*(sheet.getSticker(2)) == I);
  assert(sheet.getSticker(3) == NULL);
  assert(sheet.getSticker(6) == NULL);
  assert(sheet.translate(3, 1500, 200) == 0);
  assert(sheet.translate(0, 20, 100) == 1);

  assert(sheet.addSticker(I, 500, 200) == 3);
  assert(sheet.addSticker(I, 900, 50) == 4);
  for(int index = 0; index < 5; index++)
  {
    Image* Test = sheet.getSticker(index);
    assert(*Test == I);
  }
  assert(sheet.getSticker(100) == NULL);
  assert(sheet.getSticker(-2) == NULL);

  assert(sheet.addSticker(I, 700, 50) == -1);
  Sticker1 = sheet.render();
  //(sheet.render()).writeToFile("output2.png");

  sheet.removeSticker(0);assert(sheet.getSticker(0) == NULL);
  sheet.removeSticker(2);assert(sheet.getSticker(2) == NULL);
  sheet.removeSticker(4);assert(sheet.getSticker(4) == NULL);
  sheet.removeSticker(7);assert(sheet.getSticker(7) == NULL);
  //(sheet.render()).writeToFile("output3.png");

  assert(sheet.addSticker(I, 20, 100) == 0);
  assert(sheet.addSticker(I, 100, 20) == 2);
  assert(sheet.addSticker(I, 900, 50) == 4);
  //(sheet.render()).writeToFile("output4.png");

  assert(sheet.translate(3, 300, 20) == 1);
  assert(sheet.translate(5,200,30) == 0);
  assert(sheet.render() != Sticker1);
  //(sheet.render()).writeToFile("output5.png");

  assert(sheet.translate(3, 500, 200) == 1);
  assert(sheet.render() == Sticker1);
  //(sheet.render()).writeToFile("output6.png");

  assert(sheet.translate(3, 300, 20) == 1);
  assert(sheet.addSticker(I, 700,200) == -1);
  //(sheet.render()).writeToFile("output7.png");

  StickerSheet sheets(alma, 6);
  sheets = sheet;
  //(sheet.render()).writeToFile("output8.png");

  for(int index = -2; index < 10; index++)
  {
    Image* Test1 = sheet.getSticker(index);
    Image* Test2 = sheets.getSticker(index);
    if(Test1 != NULL){
    assert(*Test1 == *Test2);}
    if(Test1 == NULL){
    assert(Test2 == NULL);}
  }

  Sticker1 = sheet.render();
  Sticker4 = Sticker1;
  //Sticker1.writeToFile("output1.png");

  alma = I;

  assert(Sticker1 == sheet.render());
  assert(Sticker1 == sheets.render());
  //(sheet.render()).writeToFile("output9.png");
  //(sheets.render()).writeToFile("output10.png");

  alma.readFromFile("alma.png");

  I = Sticker1;
  assert(Sticker1 == sheet.render());
  assert(Sticker1 == sheets.render());
  //(sheet.render()).writeToFile("output11.png");
  //(sheets.render()).writeToFile("output12.png");

  I.readFromFile("i.png");

  sheet.removeSticker(3);
  sheet.removeSticker(-10);
  sheet.removeSticker(20);
  //(sheet.render()).writeToFile("output13.png");

  assert(sheet.translate(3, 1300, 1020) == 0);
  assert(sheet.translate(3, 300, 20) == 0);
  assert(sheets.getSticker(3) != NULL);
  assert(sheet.getSticker(3) == NULL);
  //(sheet.render()).writeToFile("output14.png");
  //(sheets.getSticker(3)).writeToFile("output15.png");

  Sticker1 = sheet.render();
  //Sticker1.writeToFile("output15.png");

  StickerSheet sheets1(sheet);
  for(int index = -2; index < 10; index++)
  {
    Image* Test1 = sheet.getSticker(index);
    Image* Test2 = sheets.getSticker(index);
    if(Test1 != NULL){
    assert(*Test1 == *Test2);}
    if(Test1 == NULL && index != 3){
    assert(Test2 == NULL);}
    if(Test1 == NULL && index == 3){
    assert(Test2 != NULL);}
  }
  //(sheets1.render()).writeToFile("output16.png");

  Image* a = sheet.getSticker(3);
  Image* a1 = sheets1.getSticker(3);
  assert(a == a1);
  //a1->writeToFile("123.png");

  assert(sheet.addSticker(I, 500, 200) == 3);
  assert(sheets1.getSticker(3) != sheet.getSticker(3));
  assert(sheets1.getSticker(3)== NULL && *(sheet.getSticker(3)) == I);
  //(sheet.render()).writeToFile("output17.png");

  assert(sheets.getSticker(10) == NULL);

  sheet.changeMaxStickers(10);
  for(int index = 0; index < 5; index++)
  {
    Image* Test = sheet.getSticker(index);
    assert(*Test == I);
  }
  for(int index = 5; index < 15; index++)
  {
    Image* Test = sheet.getSticker(index);
    assert(Test == NULL);
  }
  //(sheet.render()).writeToFile("output18.png");

  assert(sheet.getSticker(10) == NULL);
  assert(sheet.getSticker(9) == NULL);

  sheet.removeSticker(3);
  sheet.removeSticker(20);
  for(int index = -5; index < 30; index++)
  {
    Image* Test1 = sheet.getSticker(index);
    Image* Test2 = sheets1.getSticker(index);
    if(Test1 == NULL){
    assert(Test1 == Test2);}
    else{
    assert(*Test1 == I && *Test2 == I && *Test1 == *Test2);}
  }
  //(sheet.render()).writeToFile("output19.png");

  Sticker2 = sheet.render();
  assert(Sticker1 == Sticker2);
  //Sticker1.writeToFile("output20.png");
  //Sticker2.writeToFile("output21.png");

  StickerSheet Sheet_test(sheet);
  for(int index = -5; index < 30; index++)
  {
    Image* Test1 = sheet.getSticker(index);
    Image* Test2 = Sheet_test.getSticker(index);
    if(Test1 == NULL){
    assert(Test1 == Test2);}
    else{
    assert(*Test1 == I && *Test2 == I && *Test1 == *Test2);}
  }
  //(Sheet_test.render()).writeToFile("output22.png");

  assert(Sheet_test.addSticker(I, 300, 20) == 3);
  for(int index = -5; index < 30; index++)
  {
    Image* Test1 = sheets.getSticker(index);
    Image* Test2 = Sheet_test.getSticker(index);
    if(Test1 == NULL){
    assert(Test1 == Test2);}
    else{
    assert(*Test1 == I && *Test2 == I && *Test1 == *Test2);}
  }
  assert(Sheet_test.render() == sheets.render());
  //(Sheet_test.render()).writeToFile("output23.png");

  Sheet_test = sheets1;
  for(int index = -5; index < 30; index++)
  {
    Image* Test1 = sheet.getSticker(index);
    Image* Test2 = Sheet_test.getSticker(index);
    if(Test1 == NULL){
    assert(Test1 == Test2);}
    else{
    assert(*Test1 == I && *Test2 == I && *Test1 == *Test2);}
  }
  //(Sheet_test.render()).writeToFile("output24.png");

  assert(sheet.addSticker(I, 300, 20) == 3);
  assert(sheet.addSticker(I, 20, 100) == 5);
  assert(sheet.addSticker(I, 100, 200) == 6);
  assert(sheet.addSticker(I, 100, 20) == 7);
  assert(sheet.addSticker(I, 300, 20) == 8);
  assert(sheet.addSticker(I, 900, 50) == 9);
  assert(sheet.addSticker(I, 700, 50) == -1);
  //(sheet.render()).writeToFile("output25.png");

  assert(sheet.translate(10, 300, 20) == 0);
  assert(sheet.translate(-2, 300, 20) == 0);
  assert(sheet.translate(7, 300, 20) == 1);
  assert(sheet.translate(7, 100, 20) == 1);
  //(sheet.render()).writeToFile("output26.png");

  assert(sheet.render() == Sticker4);

  for(int index = 0; index <5; index++)
  {
    sheet.removeSticker(index);
  }
  assert(sheet.render() == Sticker4);
  assert(sheet.render() == sheets.render());

  sheet.removeSticker(8);
  assert(sheet.render() == sheets1.render());
  //(sheet.render()).writeToFile("output27.png");

  alma.readFromFile("alma.png");

  StickerSheet* delete_sheet = new StickerSheet(sheets);
  *delete_sheet = sheet;
  delete delete_sheet;

  delete_sheet = new StickerSheet(sheet);
  delete delete_sheet;

  delete_sheet = new StickerSheet(sheet);
  *delete_sheet = sheets1;
  delete delete_sheet;

  delete_sheet = new StickerSheet(sheet);
  *delete_sheet = sheet;
  delete delete_sheet;

  sheet.changeMaxStickers(0);
  //(sheet.render()).writeToFile("output28.png");
  assert(sheet.render() == alma);
  for(int index = -3; index < 15; index++)
  {
    assert(sheet.addSticker(I, 700, 50) == -1);
    sheet.removeSticker(index);
    Image* Test = sheet.getSticker(index);
    assert(Test == NULL);
  }

  StickerSheet* delete_sheet1 = new StickerSheet(sheets1);
  *delete_sheet1 = sheet;
  delete delete_sheet1;

  delete_sheet1 = new StickerSheet(sheet);
  delete delete_sheet1;

  delete_sheet1 = new StickerSheet(sheet);
  *delete_sheet1 = sheets1;
  delete delete_sheet1;

  delete_sheet1 = new StickerSheet(sheet);
  *delete_sheet1 = sheet;
  delete delete_sheet1;

  sheet.changeMaxStickers(6);
  //(sheet.render()).writeToFile("output29.png");
  assert(sheet.render() == alma);
  for(int index = -3; index < 15; index++)
  {
    sheet.removeSticker(index);
    Image* Test = sheet.getSticker(index);
    assert(Test == NULL);
  }

  StickerSheet* delete_sheet2 = new StickerSheet(sheet);
  delete delete_sheet2;

  delete_sheet2 = new StickerSheet(sheets1);
  *delete_sheet2 = sheet;
  delete delete_sheet2;

  delete_sheet2 = new StickerSheet(sheet);
  *delete_sheet2 = sheets;
  delete delete_sheet2;

  delete_sheet2 = new StickerSheet(sheet);
  *delete_sheet2 = sheet;
  delete delete_sheet2;

  for(int index = -3; index < 15; index++)
  {
    sheets.removeSticker(index);
    Image* Test = sheet.getSticker(index);
    assert(Test == NULL);
  }
  //(sheets.render().writeToFile("output30.png"));
  assert(sheets.render() == alma);

  sheet = sheet;

  sheet = StickerSheet(alma, 5);
  sheet.addSticker(I, 20, 200);

  Image expected;
  expected.readFromFile("tests/expected.png");

  assert( sheet.render() == expected );

  return 0;
}
