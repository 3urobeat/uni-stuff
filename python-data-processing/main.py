"""
	Beispieldatei, die die Datenverarbeitungs- sowie die Darstellungsfunktionen
	importiert, benutzt und evaluiert.

	Achtung, die Evaluation im Moodle hat weitere Testfall und ein
	paar Schutzfunktionen, die eine Manipulation der Dateien `data.py`
	und `main.py` verhindern.

	Ausserdem wird sichergestellt, dass die Implementierungen in `implementation.py`
	keine Schleifen nutzen, es muss NumPy benutzt werden!
"""

import inspect

# Fail and Pass
def code_fail():
	print("CodeIsNotValid")
	print("\t Check failed in line", inspect.getframeinfo(inspect.stack()[1][0]).lineno) 
	exit()

def code_pass():
	print("CodeIsValid (Darstellungen muessen hier manuell geprueft werden)")

## 
# Hellinger-Distanz -- NumPy
##

import math 
import numpy as np

# Referenzimplementierung 
def _hellinger_distance_py(P, Q):
	"""
		Hellinger-Distanz-Funktion für NumPy-Arrays 
		mit einfachem Python umgesetzt. 
		Diese Funktion wird zur Kontrolle des Ergebnisses benutzt.
	"""
	# Konvertiere NumPy-Array zu Python-Liste
	P, Q = P.tolist(), Q.tolist()

	H = []
	for p_i, q_i in zip(P, Q):
		h = 0
		for p_ij, q_ij in zip(p_i, q_i):
			d = math.sqrt(p_ij) - math.sqrt(q_ij)
			h += d**2
		H.append(math.sqrt(h))
	H = [1/math.sqrt(2) * h for h in H]
	
	# Konvertiere Python-Liste zu NumPy-Array
	return np.array(H)

# Daten und Implementierung laden
from data import data_1_P, data_1_Q, random_distribution_matrix
from implementation import hellinger_distance

## Test 1
diffs = hellinger_distance(data_1_P, data_1_Q) - _hellinger_distance_py(data_1_P, data_1_Q)
if np.abs(diffs).sum() > 1e-05:
	print("Hellinger-Distanz: Fehler bei Test 1 (Differenzen {})".format(diffs))
	code_fail()

## Weitere Tests
for i in range(3):
	Q = random_distribution_matrix(seed=i*234+2)
	P = random_distribution_matrix(seed=i*343+5)

	result = hellinger_distance(Q, P) 
	correct = _hellinger_distance_py(Q, P)

	if not np.allclose(correct, result):
		print("Hellinger-Distanz: Fehler bei Test {}".format(i+2))
		code_fail()

## 
# Beste Zeilen -- NumPy
##

# Referenzimplementierung 
def _select_best_rows_py(P, Q, H):
	"""
		Beste Zeilen-Funktion für NumPy-Arrays 
		mit einfachem Python umgesetzt. 
		Diese Funktion wird zur Kontrolle des Ergebnisses benutzt.
	"""
	# Konvertiere NumPy-Array zu Python-Liste
	P, Q, H = P.tolist(), Q.tolist(), H.tolist()

	best_dist = sorted(H)[0]
	
	best_index = None 
	for i, dist in enumerate(H):
		if best_dist == dist:
			best_index = i 
			break
	
	best = [ P[best_index], Q[best_index] ]

	# Konvertiere Python-Liste zu NumPy-Array
	return np.array(best)

# Implementierung laden
from implementation import select_best_rows

## Test 1
H = _hellinger_distance_py(data_1_P, data_1_Q)
diffs = _select_best_rows_py(data_1_P, data_1_Q, H) - select_best_rows(data_1_P, data_1_Q, H)
if np.abs(diffs).sum() > 1e-05:
	print("Beste Zeilen: Fehler bei Test 1 (Differenzen {})".format(diffs))
	code_fail()

## Weitere Tests
for i in range(3):
	Q = random_distribution_matrix(seed=i*44+2)
	P = random_distribution_matrix(seed=i*333+4)
	H = _hellinger_distance_py(P, Q)

	result = select_best_rows(P, Q, H)
	correct = _select_best_rows_py(P, Q, H)

	if not np.allclose(correct, result):
		print("Beste Zeilen: Fehler bei Test {}".format(i+2))
		code_fail()

code_pass()

## 
# Grafik/ Darstellung -- Matplotlib
##

# Implementierung laden
from implementation import plot_distance

# Beispiel erzeugen
#	Test 1 
Q = random_distribution_matrix(seed=12, shape=(10, 1000))
P = random_distribution_matrix(seed=13, shape=(10, 1000))
H = _hellinger_distance_py(P, Q)

p = plot_distance(H)
p.show()



