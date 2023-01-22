import numpy as np
import matplotlib.pyplot as plt

def hellinger_distance(P, Q):
	"""
		Berechnet Hellinger-Distanz zwischen zwei Matrizen.

		Args:
			- P (np.ndarray): NumPy-Array der Matrix P
			- Q (np.ndarray): NumPy-Array der Matrix Q

		Returns:
			H (np.array) mit der Hellinger-Distanz zwischen P und Q
	"""
	
	# We can't use loops, we must exclusively use numpy
	return (1 / np.sqrt(2)) * np.sqrt(np.sum(np.power(np.sqrt(P) - np.sqrt(Q), 2), axis=1)) # spooky formula with eerie formatting for a crisp +35% pain inflicting buff


def select_best_rows(P, Q, H):
	"""
		Bestimmt die beiden Zeilen, die zwischen P und Q die minimale 
		Distanz zwischen P und Q repraesentieren (wobei die Distanz mittels
		H uebergeben wird).

		Args:
			- P (np.ndarray): NumPy-Array der Matrix P
			- Q (np.ndarray): NumPy-Array der Matrix Q
			- - H (np.array): Hellinger-Distanz-Vektors (wie z.B. von
				`hellinger_distance(P, Q)` ausgegeben)

		Returns:
			Matrix mit zwei Zeilen:

				[[Zeile mit Verteilung aus P]
				 [Zeile mit Verteilung aus Q]]
	"""
	# TODO

	return np.zeros(2, P.shape[1])
	
def plot_distance(H):
	"""
		Erstellt einen Bar-Plot des Hellinger-Distanz-Vektors.
		Grafik soll dem Beispiel auf dem Aufgabenblatt moeglichst nah
		sein! 

		Args:
			- H (np.array): Hellinger-Distanz-Vektors (wie z.B. von
				`hellinger_distance(P, Q)` ausgegeben)

		Returns:
			Das Plt-Modul von Matplotlib.

			Die Grafik soll dann z.B. durch Aufruf der Funktion `show()` auf den hier 
			zurueckgegebenen Wert angezeigt werden können.
	"""
	# TODO

	return plt
