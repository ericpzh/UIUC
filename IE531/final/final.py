import numpy as np
import sys

def Frobenious_Norm(Matrix_Data):
    # write this part yourself
    T = Matrix_Data*Matrix_Data.transpose()
    m,n = T.shape
    norm = 0
    for i in range(m):
        for j in range(n):
            norm += T[i,j] * T[i,j]
    return np.sqrt(norm)

def Matrix_Sketch(Data, epsilon):
    # Edo Liberty's Matrix Sketch will have the same number of rows
    # as the original data; the #cols is ceil(2.0/epsilon)
    cols_of_sketch = np.ceil(2.0/epsilon) #l

    if (cols_of_sketch < dimension):
        Result = np.matrix(np.zeros((dimension, int(cols_of_sketch))))
    else:
        Result = np.matrix(np.zeros((dimension, dimension)))
    d, Result_len = Result.shape

    # write this part of the code yourself
    i = 0
    while i < no_of_data_points:
        j = 0
        while j < Result_len:
            if len(Result[:,j]) - np.count_nonzero(Result[:,j]) == len(Result[:,j]):
                Result[:,j] = Data[:,i]
                j = Result_len
            j += 1
        #print(Result)
        if i < no_of_data_points-1 and np.count_nonzero(Result[:,-1]) > 0:#don't have space
            u, s, vh = np.linalg.svd(Result, full_matrices=False)
            delta = s[-1]**2
            sigma_hat = np.diag([np.sqrt(np.clip(s[j]**2 - delta, 0, None)) for j in range(len(s))])
            Result = u * sigma_hat

        i += 1

    return Result

'''
dimension = 9#int(sys.argv[1])
no_of_data_points = 329#int(sys.argv[2])
epsilon = 0.2#float(sys.argv[3])
input_file = 'Data'#'matrix_sketch1'#str(sys.argv[4])
output_file = 'Data_out'#'matrix_sketch1_out'#str(sys.argv[5])

dimension = 50#int(sys.argv[1])
no_of_data_points = 1000#int(sys.argv[2])
epsilon = 0.1#float(sys.argv[3])
input_file = 'matrix_sketch1'#str(sys.argv[4])
output_file = 'matrix_sketch1_out'#str(sys.argv[5])
'''

dimension = int(sys.argv[1])
no_of_data_points = int(sys.argv[2])
epsilon = float(sys.argv[3])
input_file = str(sys.argv[4])
output_file = str(sys.argv[5])

print("Edo Liberty's Matrix Sketching Algorithm")
print("----------------------------------------")
print("Original Data-Matrix has " + str(dimension) +  "-rows & " + str(no_of_data_points) + "-cols" )
print("Epsilon = " + str(epsilon) + " (i.e. max. of " + str(100*epsilon) + "% reduction of  Frobenius-Norm of the Sketch Matrix)")
print("Input File = " + input_file)

#Read the Data
Data = []
with open(input_file, 'r') as f:
    for line in f.readlines():
        ori = line.strip().split(' ')
        Data.append([float(i) for i in ori])
Data = np.matrix(Data)



#Compute the Frobenius-Norm of the original Data-Matrix
Data_Forbenius_Norm = Frobenious_Norm(Data);
print("Frobenius Norm of the " + str(Data.shape) + " Data Matrix = " + str(Data_Forbenius_Norm))

Sketch = Matrix_Sketch(Data, epsilon)
Sketch_Forbenius_Norm = Frobenious_Norm(Sketch)
print("Frobenius Norm of the " + str(Sketch.shape) + " Sketch Matrix = " + str(Sketch_Forbenius_Norm))
print("Change in Frobenius-Norm between Sketch & Original  = ")
print(str(np.round(100*(Sketch_Forbenius_Norm - Data_Forbenius_Norm)/Data_Forbenius_Norm,3)) + "%" )
print("File `" + output_file + "' contains a " +  str(Sketch.shape) + " Matrix-Sketch")

with open(output_file, 'w+') as f:
    for row in np.array(Sketch):
        a = [str(i) for i in row]
        f.write(' '.join(a))
        f.write('\n')
#print(Sketch)
