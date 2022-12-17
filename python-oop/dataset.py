# __future__.annotations sorgt dafuer, dass man einen Typen als Hint
# 	angeben kann, auch wenn der Typ/ die Klasse noch nicht
# 	fertig definiert ist.
# 	Dann kann z.B. die Methode einer Klasse ein Objekt der Klasse selbst
#	zurueck geben.
from __future__ import annotations

# Typen und abstrakte Oberklasse
from typing import Union, List, Tuple, Any, Callable
from abc import ABC, abstractmethod

class DataSetItem():
	"""
		Objekte dieser Klasse stellen ein Element des Datensatzes dar.
		Jedes Element hat einen Namen, eine ID und einen bel. Inhalt.
	"""

	def __init__(self, name:str, id:int, content:Any):
		"""
			Ein Element mit Namen, ID und Inhalt erzeugen.
		"""
		if not isinstance(name, str) or not isinstance(id, int):
			raise TypeError("A DataSetItem requires the name to be a str and the id to be an int!")
			
		self.name = name 
		self.id = id 
		self.content = content

	def __str__(self):
		"""
			Besser lesbare Ausgabe des Objekts.
		"""
		return "Item {name} ({id}) containing {t}{p}".format(
				name=self.name, id=self.id,
				t=type(self.content).__name__,
				p=" ("+self.content[:10]+"...)" if isinstance(self.content, str) else "" 
			)

class DataSetInterface(ABC):
	"""
		Die Oberklasse/ das Interface fuer die Klasse `DataSet`, die in dieser
		Aufgabe zu erstellen ist.

		Die Klasse `DataSet` muss in der Datei `implementation.py` erstellt werden!
	"""

	ITERATE_SORT_BY_NAME = "name"
	ITERATE_SORT_BY_ID = "id"

	def __init__(self, items:Union[List[DataSetItem], Tuple[DataSetItem, ...]]=[]):
		"""
			Erstellt einen neuen Datensatz und fuegt direkt `DataSetItems` ein, wenn
			welche uebergeben wurden.

			Args:
				- items (iterable of DataSetItem): Elemente zum Hinzufuegen
		"""
		# Standardwerte fuer die Iteration (sortiert und wonach)
		self.iterate_sorted = True 
		self.iterate_reversed = False 
		self.iterate_key = self.ITERATE_SORT_BY_NAME

	def set_iteration(self, sort=None, reverse=None, key=None):
		"""
			Änderung der Reihenfolge bei Iteration ueber die Elemente.

			Args:
				- sort (bool): Soll über die DataSetItems sortiert oder in der Reihenfolge des Einfügens iteriert werden
				- reverse (bool): Analog zu sort() von Python (auf- oder absteigend sortieren)
				- key (str): Entweder ITERATE_SORT_BY_NAME oder ITERATE_SORT_BY_ID, falls nach Name des DataSetItem oder nach ID des DataSetItem sortiert iteriert werden soll.
		"""
		if sort is not None:
			self.iterate_sorted = bool(sort)
		if reverse is not None:
			self.iterate_reversed = bool(reverse)
		if key is not None:
			self.iterate_key = self.ITERATE_SORT_BY_ID if key == self.ITERATE_SORT_BY_ID else self.ITERATE_SORT_BY_NAME

	@abstractmethod
	def __setitem__(self, name:str, id_content:Tuple[int, Any]):
		"""
			Hinzufuegen von neuen Daten

			Args:
				- name (str): Name des Datums
				- id_content (tuple[int, any]): ID und Inhalt des Datum

			Achtung, hier muss zuerst immer ein DataSetItem erzeugt werden, bevor die
			Daten hinzugefuegt werden!
		"""
		pass

	@abstractmethod
	def __iadd__(self, item:DataSetItem):
		"""
			Hinzufuegen eines DataSetItems

			Args:
				- item (DataSetItem): Hinzuzufuegendes DataSetItem

			Achtung, die Namen der Items sind eindeutig, fuegt man zweimal den 
			gleichen Namen ein, so ueberschreibt das neuere Item das aeltere.

			Returns?! [Anmerkung, selbst zu pruefen, ob notwendig]
		"""
		pass

	@abstractmethod
	def __delitem__(self, name:str):
		"""
			Loeschen eines Datum.

			Args:
				- name (str): Name des zu loeschenden DataSetItem
		"""
		pass

	@abstractmethod
	def __contains__(self, name:str) -> bool:
		"""
			Pruefung ob ein DataSetItem im DataSet 

			Args:
				- name (str): Name des gesuchten DataSetItem

			Returns:
				bool
		"""
		pass

	@abstractmethod
	def __getitem__(self, name:str) -> DataSetItem:
		"""
			Abrufen eines DataSetItem

			Args:
				- name (str): Name des gewuenschten DataSetItem

			Returns:
				DataSetItem
		"""
		pass

	@abstractmethod
	def __and__(self, dataset:DataSetInterface) -> DataSetInterface:
		"""
			Schnittmenge zwischen zwei DataSets erzeugen.
			Es werden die Namen der DataSetItems als Schluessel genutzt.
			
			Args:
				- dataset (DataSetInterface): Das andere DataSet 

			Returns:
				Neues DataSet, nur mit den DataSetItems im Schnitt

			Er werden die DataSetItems aus `self` in das Ergebnis uebernommen!
		"""
		pass

	@abstractmethod
	def __or__(self, dataset:DataSetInterface) -> DataSetInterface:
		"""
			Vereinigung von zwei DataSets erzeugen.
			Es werden die Namen der DataSetItems als Schluessel genutzt.

			Args:
				- dataset (DataSetInterface): Das andere DataSet 
				
			Returns:
				Neuen Datensatz, mit allen DataSetItems in beiden DataSets (=der Vereinigung)

			Gibt es zwei Namen doppelt, so werden die DataSetItems aus 
			`dataset` in das Ergebnis uebernommen!
		"""
		pass

	@abstractmethod
	def __iter__(self):
		"""
			Iteration ueber die DataSetItems des DataSets.

			Dabei ist die Sortierung nach den Attributen 
				`self.iterate_sorted` 
				`self.iterate_reversed`
				`self.iterate_key`
			zu beachten.

			Funktion soll zur Iteration genutzt werden koennen:
				`for item in d:`
		"""
		pass

	@abstractmethod
	def filtered_iterate(self, filter:Callable[[str, int], bool]):
		"""
			Iteration ueber die DataSetItems des DataSets.
			Dabei koennen die DataSetItems gefiltert werden.
			Die Sortierung ist wie bei __iter__ zu beachten.

			Args:
				- filter (callable): z.B. eine Lambda-Funktion mit zwei Parametern.
					Die Funktion bekommt Name und ID jedes DataSetItems und das DataSetItem
					wird nur ausgegeben, wenn die Funktion `True` zurueck gibt.
		
			Funktion soll zur Iteration genutzt werden koennen:
				`for item in d.filtered_iterate(lambda s,i:True):`
		"""
		pass 

	@abstractmethod
	def __len__(self) -> int:
		"""
			Laenge des Objekts bestimmen.

			Returns:
				Anzahl der DataSetItems im DataSet
		"""
		pass	

	def __str__(self):
		"""
			Besser lesbare Ausgabe des Objekts.

			Nutzt oben definierte Methoden, falls diese einen Fehler verursachen,
			kann die Methode `__str__` auch geloescht/ veraendert werden!
		"""
		return  "DataSet mit {n} Elementen:\n\t - {items}".format(
			n=len(self),
			items='\n\t - '.join([str(i) for i in self])
		)
			
