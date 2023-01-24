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

	minRes = H.argmin() # Get index of smallest element

	return np.array([ P[minRes], Q[minRes] ]) # Create new matrix and return it


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

	# Create a new matplotlib figure using these sizes and give it a name
	plt.figure(figsize=(10, 7))
	plt.title("Hellinger-Distanzen")

	# Give both axis labels
	plt.xlabel("Verteilung (Matrixzeile)")
	plt.ylabel("Distanz")

	# Create an array of indices for our x axis
	indexArr = np.arange(0, len(H))

	# Create a bar for each element of H, index on x-axis, value on y-axis
	plt.bar(indexArr, H, label="HD")

	# Add a legend to the figure which will display our blue bar as "HD" and move it to look like the given example
	plt.figlegend(loc="upper right", bbox_to_anchor=(0.985, 0.945))

	# Compress everything to use less space
	plt.tight_layout()

	# Return plt obj so main.py can display it
	return plt
