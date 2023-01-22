import numpy as np

# kleine Matrix mit Beispielverteilungen
data_1_P = np.array([
	[0.3, 0.2, 0.1, 0.2, 0.2],
	[0.3, 0.1, 0.2, 0.2, 0.2],
	[0.2, 0.2, 0.2, 0.2, 0.2]
])
data_1_Q = np.array([
	[0.3, 0.2, 0.1, 0.2, 0.2],
	[0.3, 0.2, 0.1, 0.2, 0.2],
	[0.3, 0.2, 0.1, 0.2, 0.2]
])

# Erzeugen von zufaelligen Matrizen mit Verteilungen
def random_distribution_matrix(seed:int=1234, shape=(100, 1000)):
	np.random.seed(seed=seed)
	M = np.random.random(size=shape)
	M /= np.expand_dims(M.sum(axis=1), axis=1) # Summe jeder Zeile muss 1 ergeben!
	return M