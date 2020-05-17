# %load HMM.py
from __future__ import print_function

from tabulate import tabulate
import numpy as np
import pdb


class HMM(object):

    def __init__(self, A, B, pi0=None, states=None, emissions=None):
        """
        :param A: Transition matrix of shape (n, n) (n = number of states)
        :param B: Emission matrix of shape (n, b) (b = number of outputs)
        :param pi0: Initial State Probability vector of size n, leave blank for uniform probabilities
        :param states: State names/labels as list
        :param emissions: Emission names/labels as list
        """
        self.A = A
        self.B = B
        self.n_states = A.shape[0]
        self.n_emissions = B.shape[1]
        self.states = states
        self.emissions = emissions
        self.pi0 = pi0

        if pi0 is None:
            self.pi0 = np.full(self.n_states, 1.0 / self.n_states)

        if states is None:
            self.states = [chr(ord('A') + i) for i in range(self.n_states)]

        if emissions is None:
            self.emissions = [str(i) for i in range(self.n_emissions)]
        
    def print_matrix(self, M, headers=None):
        """
        Print matrix in tabular form

        :param M: Matrix to print
        :param headers: Optional headers for columns, default is state names
        :return: tabulated encoding of input matrix
        """
        headers = headers or self.states

        if M.ndim > 1:
            headers = [' '] + headers
            data = [['t={}'.format(i + 1)] + [j for j in row] for i, row in enumerate(M)]
        else:
            data = [[j for j in M]]
        print(tabulate(data, headers, tablefmt="grid", numalign="right"))
        return None
        
    def forward_algorithm(self, seq):
        
        
        """
        Apply forward algorithm to calculate probabilities of seq

        :param seq: Observed sequence to calculate probabilities upon
        :return: Alpha matrix with 1 row per time step
        """
        Alphas = []
        Zs = []
        
        T = len(seq)

        # Initialize forward probabilities matrix Alpha
        Alpha = np.zeros((T, self.n_states))
        
        a = self.B.transpose()[seq[0]] * self.pi0
        Z = sum(a)
        a = a / Z
        
        Alpha[0] = a
        #Zs.append(np.log(Z))
        
        # Your implementation here
        for t in range(1, T):
            a = self.B.transpose()[seq[t]] * np.matmul(np.transpose(self.A),a)
            Z = sum(a)
            a = a / Z

            Alpha[t] = a
            #Zs.append(np.log(Z))
        
        return Alpha#, sum(Zs)    

    def backward_algorithm(self, seq):
        """
        Apply backward algorithm to calculate probabilities of seq

        :param seq: Observed sequence to calculate probabilities upon
        :return: Beta matrix with 1 row per timestep
        """

        T = len(seq)

        ## Initialize backward probabilities matrix Beta
        Beta = np.zeros((T, self.n_states))
        
        b = np.ones(self.n_states)
        Beta[T-1] = b
        # Your implementation here
        
        for t in range(T-1, 0, -1):
            b = np.matmul(self.A, self.B.transpose()[seq[t]] * b)
            Beta[t-1] = b
            
        return Beta

    def forward_backward(self, seq):
        """
        Applies forward-backward algorithm to seq

        :param seq: Observed sequence to calculate probabilities upon
        :return: Gamma matrix containing state probabilities for each timestamp
        :raises: ValueError on bad sequence
        """

        # Convert sequence to integers
        if all(isinstance(i, str) for i in seq):
            seq = [self.emissions.index(i) for i in seq]

        # Infer time steps
        T = len(seq)
        
        # Calculate forward probabilities matrix Alpha
        Alpha = self.forward_algorithm(seq)
        # Initialize backward probabilities matrix Beta
        Beta = self.backward_algorithm(seq)

        # Initialize Gamma matrix
        Gamma = np.zeros((T, self.n_states))        
        
        # Your implementation here
        Gamma = Alpha * Beta
        for i in Gamma:
            i /= sum(i)
        
        print('Alpha:')
        print(Alpha)
        print('Beta:')
        print(Beta)
        print('Gamma:')
        print(Gamma)
        
        return Gamma