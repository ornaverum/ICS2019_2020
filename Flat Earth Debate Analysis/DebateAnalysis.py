
import numpy as np
import pandas as pd

res_df = pd.read_excel("Debate Responses.xlsx", sheet_name="Sheet1")

res_df.fillna("", inplace=True);

aff_votes = res_df[res_df["Vote"]=="Aff"]["Vote"].count()
neg_votes = res_df[res_df["Vote"]=="Neg"]["Vote"].count()

print("There are {} affirmative votes and {} negative votes.\n\n" .format(aff_votes, neg_votes))


arg_list_aff = []
arg_list_neg = []
ev_list_aff = []
ev_list_neg = []

comment_list = []

def findEvList(voteType, colType):
	com_list = []
	for x in res_df[res_df["Vote"]==voteType][colType]:
		for y in x.split(","):
			if y:
				com_list.append(y.strip())
	return com_list


def findCommentList():
	com_list = []
	for x in res_df["Comments"]:
		if x:
			com_list.append(x)
	return com_list
	
arg_list_aff = findEvList("Aff", "Arguments")
arg_list_neg = findEvList("Neg", "Arguments")
ev_list_aff = findEvList("Aff", "Evidence")
ev_list_neg = findEvList("Neg", "Evidence")
com_list = findCommentList();


df_arg_aff = pd.DataFrame(arg_list_aff, columns = ["Arg"]);
df_arg_neg = pd.DataFrame(arg_list_neg, columns = ["Arg"]);
df_ev_aff = pd.DataFrame(ev_list_aff, columns = ["Ev"]);
df_ev_neg = pd.DataFrame(ev_list_neg, columns = ["Ev"]);

print("People mentioned these arguments for the affirmative team")
print(df_arg_aff["Arg"].value_counts())

print("\n\nPeople mentioned this evidence for the affirmative team\n\n")
print(df_ev_aff["Ev"].value_counts())

input("Press Enter to continue...")

print("\n\nPeople mentioned these arguments for the negative team")
print(df_arg_neg["Arg"].value_counts())

print("\n\nPeople mentioned this evidence for the negative team\n\n\n")
print(df_ev_neg["Ev"].value_counts())

input("Press Enter to continue...")

print("\n\n People had these comments\n")
for x in com_list:
	print(x)


input("\n\nPress Enter to continue...")

aff_votes = res_df[res_df["Enjoy"]=="Aff"]["Enjoy"].count()
neg_votes = res_df[res_df["Enjoy"]=="Neg"]["Enjoy"].count()

print("{} enjoyed the affirmative the most and {} enjoyed the negative more.\n\n" .format(aff_votes, neg_votes))



