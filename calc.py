'''
File: calc.py
Project: python-calculator
Created Date: 04.11.2022 13:23:24
Author: 3urobeat

Last Modified: 04.11.2022 14:22:48
Modified By: 3urobeat

Copyright (c) 2022 3urobeat <https://github.com/HerrEurobeat>

Licensed under the MIT license: https://opensource.org/licenses/MIT
Permission is granted to use, copy, modify, and redistribute the work.
Full license information available in the project LICENSE file.
'''


import sys

# Map all words to actual numbers/operations to make replacing them easier
wordMappings      = { "null": 0, "eins": 1, "zwei": 2, "drei": 3, "vier": 4, "fünf": 5, "sechs": 6, "sieben": 7, "acht": 8, "neun": 9,
                      "plus": "+", "minus": "-", "mal": "*", "durch": "/", "gleich": "=" }


""" Get all lines from stdin """
def readInput():
    tasks = []

    # Iterate over all lines from stdin
    for line in sys.stdin:
        line = line.lower() # Ignore case

        # Convert all words to numbers/operations
        for e in wordMappings:
            line = line.replace(e, str(wordMappings[e]))

        tasks.append(line)

    return tasks
