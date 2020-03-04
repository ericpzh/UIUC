// Your code here!
#include "exam.h"
#include "matrix.h"


namespace exam{
  Matrix flip_rows(const Matrix &m){
      int row = m.get_num_rows();
      int column = m.get_num_columns();
      Matrix* result = new Matrix(row , column);
      for(int i = 0 ; i < row-1 ; i += 2){
        for(int j = 0; j < column ; j++){
          int a = m.get_coord(i , j);
          int b = m.get_coord(i+1 , j);
          result->set_coord(i , j , b);
          result->set_coord(i+1, j ,a);
        }
      }
      if(row % 2 == 1){
        for(int j = 0; j < column ; j++){
          int c = m.get_coord(row -1, j);
          result->set_coord(row-1,j, c);
        }
      }
      return *result;
  }
}
