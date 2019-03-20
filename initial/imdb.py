import pandas as pd
import numpy as np
import csv



if __name__ == '__main__':
	df = pd.read_csv('imdb.csv',
		header=None)

	#print df
	actors = df[5]
	year = df[6]
	rating = df[8]
	#print actors[:3]
	#print year[:3]
	#print rating[:3]

	new_df = pd.concat((actors,year), axis=1)
	new_df = pd.concat((new_df,rating), axis=1)

	#print new_df[:3]

	f = open('imdb_highrated.csv', 'r') 
	
	nodes=[]
	actors=[]

	for line in f.readlines():
		line = line.replace(' ','')
		line = line.replace('"','')
		line = line.replace('\n','')
        
		#print(line)
		parts = line.split(',')

		for part in parts:

		  if part not in nodes:
			 nodes.append(part)
		print nodes

	with open('nodes_highrated.csv', 'w') as output:
		
		for i,val in enumerate(nodes):
			
			output.write('%d,%s \n' % (i,val))



	