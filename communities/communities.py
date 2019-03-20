import pandas as pd 
import numpy as np 
import itertools

input_mod = pd.read_csv('nodes_degree_mod_full.csv', header=None)
input_imdb = pd.read_csv('imdb.csv', header=None)

movies = []
for i in input_imdb[0].tolist():
	#print i
	tmp = []
	for m in i.split(","):
		tmp.append(m.replace(" ",""))
	movies.append(tmp)			#movies: each row represent a movie with its actors

#print movies

actors = input_mod[0].tolist()
cluster = input_mod[1].tolist()

count = cluster[0]
tmp = []
big_cluster = []
for i in actors:					#put actors that belong to the same community in the same list
	mod = cluster[actors.index(i)]
	if mod == count:
		s = i.rstrip()
		tmp.append(s)
	else:
		big_cluster.append(tmp)		#big cluster: each row represent a cluster with a list of its actors
		count = cluster[actors.index(i)]
		tmp = []
		tmp.append(i)

#print big_cluster

comb = []
for c in big_cluster:
	for sub in itertools.combinations(c,2):		#produce all pair combinations for actors in same community
		comb.append(sub)
#print comb

collab = []
for c in movies:
	for sub in itertools.permutations(c,2):		#produce all pair combinations for actors in same movie
		collab.append(sub)

#print collab

rec = []
for i in comb:
	if i not in collab: 	#if a pair of actors in the same cluster is not contained in movie collaborations
		#print i
		rec.append(i)		#then keep this combination
#print rec

df = pd.DataFrame({'recommendations':rec})
df.to_csv("recommendation2.csv")

