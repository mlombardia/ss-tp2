
from cmath import sqrt
from os import times
import numpy as np
import math
from matplotlib import pyplot as plt
from statistics import mean 

# Read input
file = open("../OutputTime.csv", 'r')
InputLines = file.readlines()

times0 = []
pL = []
pR = []

count = 0

for line in InputLines:
    if count >= 1:
        str = line.strip().split(',')
        times0.append(float(str[0]))
        str = line.strip().split(',')
        pL.append(float(str[1]))
        str = line.strip().split(',')
        pR.append(float(str[2]))
    count += 1

fig, ax = plt.subplots()
ax.scatter(times0, pR)

##y_error = 20*0.10             ## El 10% de error
#y_error = [stdDev1, stdDev2, stdDev3, stdDev4, stdDev5, stdDev6, stdDev7]


#plt.errorbar(n,averageTime, yerr = y_error, capsize = 3)
ax.set_title("Fraccion de particulas a traves del tiempo con N=5000")
ax.set_xlabel('Tiempo (ms)')
ax.set_ylabel('Particulas lado derecho/Particulas totales')


plt.show()
plt.close(fig)




