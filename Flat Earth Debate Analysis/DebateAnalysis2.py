
import numpy as np
import pandas as pd

res_df = pd.read_excel("Debate Responses.xlsx", sheet_name="Sheet1")

res_df.fillna("", inplace=True);

aff_votes = res_df[res_df["Vote"]=="Aff"]["Vote"].count()
neg_votes = res_df[res_df["Vote"]=="Neg"]["Vote"].count()

print("There are {} affirmative votes and {} negative votes.\n\n" .format(aff_votes, neg_votes))



#res_df["Arguments"] = res_df["Arguments"].str.split(",", n=1)
#res_df["Evidence"] = res_df["Evidence"].str.split(",", n=1)

print(res_df[["Arguments", "Evidence"]].apply(lambda x: , axis=1))

res_df["Arguments"] = res_df[["Arguments", "Evidence"]].join("")
res_df.drop("Evidence", axis=1);
print(res_df)


