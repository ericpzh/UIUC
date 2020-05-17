from __future__ import print_function
import numpy as np

from HMM import HMM

"""
Example from ICA 4
"""

A = np.array([[0.40, 0.60],
              [0.80, 0.20]])

B = np.array([[0.40, 0.60],
              [0.70, 0.30]])

pi0 = np.array([0.90, 0.10])


seq = ['WB', 'PS', 'WB']

model = HMM(A, B, pi0,
            states=['BS', 'CS'],
            emissions=['PS', 'WB'])

res = model.forward_backward(seq)
model.print_matrix(res)
