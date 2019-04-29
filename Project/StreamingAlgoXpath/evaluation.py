import matplotlib.pyplot as plt
import numpy as np

x = [136,272,408,544,680,816]
y = [0.005811,0.007887,0.0088498,0.010516,0.012152,0.0129496]
plt.plot(x,y,'r-o')
plt.xlabel("size of file(bytes)")
plt.ylabel("execution time(s)")
# plt.ylabel("memory usage(bytes)")
plt.show()