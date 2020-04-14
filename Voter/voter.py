
import numpy as np

num_voters = 100
num_candidates = 5
num_dims = 1
val_low = -2
val_mode = 0
val_high = 2


v = np.random.triangular(val_low, val_mode, val_high, num_voters)

a = [num_candidates*[i] for i in v]

c = np.array([list(range(val_low, val_high + 1))]*num_voters)

d = np.abs(c-a)

voter_prefs = np.argsort(d, axis=1)

votes = np.bincount(voter_prefs[:,0])

winner = np.argmax(votes)

# ~ print(np.array(v))
# ~ print(np.array(a))
# ~ print(c)
# ~ print(d)
print(voter_prefs)
print(votes)
print("{} is the FPTP winner !" .format(winner))

round_num = 0
if votes[winner] < 0.5*np.sum(votes):
	print("No Instant Runnoff Winner Yet")
	loser = min(num_candidates - round_num -1, len(votes))
	print("{} is the loser" .format(loser))
	for i in range(num_voters):
		j = voter_prefs[i,:] == loser
		print(j)
		l = lambda j: voter_prefs[i, j] == loser for j in range(num_candidates)
		print(l)
		replacement = voter_prefs[i, max(j)+1]
		voter_prefs[i, j] = replacement
	print("Instant Runnoff R1" .format(winner))
	voter_prefs = np.argsort(d, axis=1)

	votes = np.bincount(voter_prefs[:,0])

	winner = np.argmax(votes)
	print(d)
	print(voter_prefs)
	print(votes)
	print("{} is the IR winner !" .format(winner))
	


# ~ def defineVoterPrefs(num_voters = 100, num_candidates = 5):
	# ~ return (num_candidates)*np.random.rand(num_voters,1) - 0.5

# ~ def findPrefCandidates(voter_prefs):
	
	# ~ voter_prefs_mat = np.dot(voter_prefs, np.ones([1, num_candidates]))
	# ~ cands = np.array(num_voters*[list(range(num_candidates))])
	
	# ~ voter_prefs = np.argsort(np.absolute(voter_prefs_mat - cands), axis=1)
	
	# ~ return np.round(voter_prefs)

# ~ voter_prefs = defineVoterPrefs(num_voters, num_candidates)


# ~ voter_votes = findPrefCandidates(voter_prefs)


# ~ candidate_votes = np.zeros(num_candidates)

# ~ for i in range(num_candidates):
	# ~ candidate_votes[i] = np.sum(voter_votes[:,0]==i)
	
# ~ print(candidate_votes)

# ~ candidate_order = np.argsort(candidate_votes)

# ~ print(candidate_order)

# ~ print('The FPTP winner is {}!' .format(candidate_order[-1]))

# ~ print("Running instant runoff")

# ~ ir_candidates = list(range(num_candidates))
# ~ ir_candidates.remove(candidate_order[0])

# ~ candidate_votes = np.zeros(len(ir_candidates))

# ~ j = 0
# ~ for i in ir_candidates:
	# ~ candidate_votes[j] = np.sum(voter_votes[:,0]==ir_candidates[i])
	# ~ j = j + 1

# ~ print(candidate_votes)

