import urllib.request
import zipfile,io,shutil,os,glob
import time
import random
import urllib.parse
from subprocess import call

def getLinks(s):
    chunks=s.split(b"href=\"")[1:]
    out=[c.split(b"\"")[0] for c in chunks]
    return [o for o in out if b"github" in o]
    
def download(username,repo,commit,folder,dest):
    f='https://github.com/%s/%s/archive/%s.zip'%(username,repo,commit)
    # ~ print(f)
    response  = urllib.request.urlopen(f)
    data = response .read()
    z = zipfile.ZipFile(io.BytesIO(data))
    #print(folder)
    #print(z.namelist())
    try:
        os.mkdir("grade")
    except:
        pass
    try:
        os.mkdir("grade/"+username)
    except:
        pass
    for f in z.namelist():
        if f.split("/")[-2]==folder and f.split("/")[-1]!="":
            #print(f,f.split("/")[-1])
            print(f)
            with z.open(f) as zf, open("grade/"+username+"/"+f.split("/")[-1], 'wb') as g:
                shutil.copyfileobj(zf, g)
    #input()
    #print(z)

def showFile(f):
    for line in open(f):
        print(line.rstrip())

s=open(input("input file: "),"rb").read()
links=getLinks(s)
print(links)
for link in links:
	# ~ if('Assignment' in link.decode('utf-8')):
	username,repo,_,commit,folder=urllib.parse.unquote(link.decode('utf-8')).split("/")[3:]
	print(username,repo,commit,folder)
	download(username,repo,commit,folder,"")

folders=glob.glob("grade\*")
cwd = os.getcwd()
#random.shuffle(files)
for f in folders:
    print(f)
    os.chdir(f)
    try:
        pass
        # ~ call("javac *.java")
        # ~ call("java Program.java")
        call("python Program.py")
        input("===========end of run=============")
        showFile("Program.py")
    except:
        pass
        print("whoops")
    os.chdir(cwd)
