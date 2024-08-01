import matplotlib.pyplot as plt
import networkx as nx
import random
import time
import tkinter as tk
from tkinter import simpledialog

# This function checks that there are undefended provinces with respect to the problem or not.

def IsFeasible(G):
    x = 0
    y = 0
    z = 0
    for node in G.nodes:
        if G.nodes[node]["Number"] == 0:
            y = 0
            x = x+1
            neighbor_list = [n for n in G.neighbors(node)]
            for neighbor in neighbor_list:
                z+=1
                num = G.nodes[neighbor]["Number"]
                if num == 2:
                    y = y+1
            if y == 0 and z > 0:
                break
            z = 0

    if x == 0:
         return True
    else:
        if y == 0 and z > 0:
            return False
        else:
            return True


#  This function produces an initial feasible solution X∗ by applying random changes to
#  elements of the zero vector X.

def InitialSolution(G):
    
    while (True):
        y = random.randint(1,2)
        y2 = random.randint(0,G.number_of_nodes()-1)
        a = chr(ord("A") + y2)
        G.nodes[a]["Number"] = y
        control = IsFeasible(G)
        if control == True:
            break

    for node in G.nodes:
        if G.nodes[node]["Number"] > 0:
            G.nodes[node]["Number"] -= 1
            control2 = IsFeasible(G)
            if control2 == False:
                G.nodes[node]["Number"] += 1


# This function randomly chooses an element of the solution X with positive value and decreases its value by one. If the
# resulting vector is again a feasible solution, it stores it as the new best solution
# and repeats the process until an infeasible solution is found.

def DecreasingProcedure(X,X2):
    
    while True:
                y = random.randint(0,X2.number_of_nodes()-1)
                a = chr(ord("A") + y)
                
                if X2.nodes[a]["Number"] > 0:
                    X2.nodes[a]["Number"] -= 1
                    control = IsFeasible(X2)
                    if control == False:
                        break
                    else:
                        for node in X2.nodes:
                            X.nodes[node]["Number"] = X2.nodes[node]["Number"]


def AssignGraph(X):
    X2=nx.Graph()
    for node in X.nodes:
        X2.add_node(node,Number = X.nodes[node]["Number"])
    
    for edge in X.edges:
        X2.add_edge(edge[0],edge[1])

    return X2


def Shake(X,k,nodeNum):

    X2=nx.Graph()
    
    for node in X.nodes:
        X2.add_node(node,Number = X.nodes[node]["Number"])
    
    for edge in X.edges:
        X2.add_edge(edge[0],edge[1])

    DecreasingProcedure(X,X2)

    for j in range (k):
        while True:
            y = random.randint(0,nodeNum-1)
            a = chr(ord("A") + y)
            if X2.nodes[a]["Number"] < 2:
                break
            
        while True:
            y = random.randint(0,nodeNum-1)
            b = chr(ord("A") + y)
            if X2.nodes[b]["Number"] > 0:
                break
            
        X2.nodes[a]["Number"] += 1
        X2.nodes[b]["Number"] -= 1
    
    control = IsFeasible(X2)
    if control == True: 
        for node in X2.nodes:
            X.nodes[node]["Number"] = X2.nodes[node]["Number"]
        DecreasingProcedure(X,X2)
    
    return X2


# This function calculates the number of undefended provinces with respect to the problem.

def penalty(X):
 y = 0
 z = 0
 undefended = 0
 for node in X.nodes:
    if X.nodes[node]["Number"] == 0:
        y = 0
        neighbor_list = [n for n in X.neighbors(node)]
        for neighbor in neighbor_list:
            z+=1
            num = X.nodes[neighbor]["Number"]
            if num == 2:
               y = y+1
        if y == 0 and z > 0:
            undefended+=1
        z = 0
 return undefended


def LocalSearch2(X,nodeNum):
    ndmin = penalty(X)
    X2 = nx.Graph()
    X3 = nx.Graph()
    X4 = nx.Graph()
    X5 = nx.Graph()
    control = False
    control2 = False
    check = False
    p = 0.5
    count = 0

    while count == 0:
     control = False
     control2 = False
     for i in range(nodeNum):
        if X.nodes[chr(ord("A") + i)]["Number"] == 2:
            X.nodes[chr(ord("A") + i)]["Number"] -=2
            for j in range(nodeNum):
                if X.nodes[chr(ord("A") + j)]["Number"] < 2:
                    X.nodes[chr(ord("A") + j)]["Number"] += 1
                    check = IsFeasible(X)
                    if check == True:
                        for node in X.nodes:
                                X2.add_node(node,Number = X.nodes[node]["Number"])
                            
                        for edge in X.edges:
                                X2.add_edge(edge[0],edge[1])
                        
                        DecreasingProcedure(X,X2)
                        ndmin = penalty(X2)
                        continue
                    else:
                        for k in range(nodeNum):
                         if X.nodes[chr(ord("A") + k)]["Number"] <2:
                            X.nodes[chr(ord("A") + k)]["Number"] += 1
                            check = IsFeasible(X)
                            if check == True:
                                for node in X.nodes:
                                        X2.add_node(node,Number = X.nodes[node]["Number"])
                                    
                                for edge in X.edges:
                                        X2.add_edge(edge[0],edge[1])
                                
                                DecreasingProcedure(X,X2)
                                ndmin = penalty(X2)
                                continue

                            else:
                                nd = penalty(X)
                                if nd < ndmin:
                                    for node in X.nodes:
                                        X3.add_node(node,Number = X.nodes[node]["Number"])
                                    
                                    for edge in X.edges:
                                        X3.add_edge(edge[0],edge[1])
                                    ndmin = nd
                                    control = True
                                if nd == ndmin:
                                    num = 1/p
                                    randNum = random.randint(1,num)
                                    if randNum == num:
                                        for node in X.nodes:
                                            X4.add_node(node,Number = X.nodes[node]["Number"])
                                        
                                        for edge in X.edges:
                                            X4.add_edge(edge[0],edge[1])
                                        control2 = True
                            X.nodes[chr(ord("A") + k)]["Number"] -= 1
                X.nodes[chr(ord("A") + j)]["Number"] -=1
            X.nodes[chr(ord("A") + i)]["Number"] +=2

     if control == True:
            for node in X3.nodes:
                X.add_node(node,Number = X3.nodes[node]["Number"])
            
            for edge in X3.edges:
                X.add_edge(edge[0],edge[1])
     else:
            count+=1
            if control2 == True:
                for node in X4.nodes:
                    X.add_node(node,Number = X4.nodes[node]["Number"])
                
                for edge in X4.edges:
                    X.add_edge(edge[0],edge[1])
            else:
                break
    
    for node in X2.nodes:
        X5.add_node(node,Number = X2.nodes[node]["Number"])
    
    for edge in X2.edges:
        X5.add_edge(edge[0],edge[1])
    return X5


def LocalSearch(X,nodeNum):
    
    ndmin = penalty(X)
    X2 = nx.Graph()
    X3 = nx.Graph()
    X4 = nx.Graph()
    X5 = nx.Graph()
    control = False
    control2 = False
    p = 0.5
    count = 0

    while count == 0:
     control = False
     control2 = False
     for i in range(nodeNum):
        if X.nodes[chr(ord("A") + i)]["Number"] > 0:
            X.nodes[chr(ord("A") + i)]["Number"] -=1
            check = IsFeasible(X)
            if check == True:
               for node in X.nodes:
                    X2.add_node(node,Number = X.nodes[node]["Number"])
                
               for edge in X.edges:
                    X2.add_edge(edge[0],edge[1])
               
               DecreasingProcedure(X,X2)
               ndmin = penalty(X2)
               continue
            else:
                for j in range(nodeNum):
                   if j != i and X.nodes[chr(ord("A") + j)]["Number"] <2:
                     X.nodes[chr(ord("A") + j)]["Number"] += 1
                     nd = penalty(X)
                     if nd == 0:
                        for node in X.nodes:
                            X2.add_node(node,Number = X.nodes[node]["Number"])
                        
                        for edge in X.edges:
                            X2.add_edge(edge[0],edge[1])
                        DecreasingProcedure(X,X2)
                        ndmin = penalty(X2)
                        continue
                     else:
                        if nd < ndmin:
                           for node in X.nodes:
                             X3.add_node(node,Number = X.nodes[node]["Number"])
                        
                           for edge in X.edges:
                             X3.add_edge(edge[0],edge[1])
                           ndmin = nd
                           control = True
                        if nd == ndmin:
                            num = 1/p
                            randNum = random.randint(1,num)
                            if randNum == num:
                                for node in X.nodes:
                                    X4.add_node(node,Number = X.nodes[node]["Number"])
                                
                                for edge in X.edges:
                                    X4.add_edge(edge[0],edge[1])
                                control2 = True
                     X.nodes[chr(ord("A") + j)]["Number"] -= 1
            X.nodes[chr(ord("A") + i)]["Number"] +=1

     if control == True:
            for node in X3.nodes:
                X.add_node(node,Number = X3.nodes[node]["Number"])
            
            for edge in X3.edges:
                X.add_edge(edge[0],edge[1])
     else:
            count+=1
            if control2 == True:
                for node in X4.nodes:
                    X.add_node(node,Number = X4.nodes[node]["Number"])
                
                for edge in X4.edges:
                    X.add_edge(edge[0],edge[1])
            else:
                X2 = LocalSearch2(X,nodeNum)
    
    for node in X2.nodes:
        X5.add_node(node,Number = X2.nodes[node]["Number"])
    
    for edge in X2.edges:
        X5.add_edge(edge[0],edge[1])
    return X5
   

# This function calculates the weight of the graph.

def weight(G):
    sum = 0
    for node in G.nodes:
           sum += G.nodes[node]["Number"]
    return sum


# This function solves a problem through exhaustion: it goes through all possible choices until a solution is found.

def BruteForce(G):

    G2=nx.Graph()

    sum = 1000
    node_num = G.number_of_nodes()
    count = 0
    same = 0

    rows, cols = (100000, node_num)
    arr = [[0]*cols]*rows
    rows2, cols2 = (1, node_num)
    arr2 = [0]*node_num
    control = True
    t = 0
    finish = 0
    num = 1

    while True:
          control = True
          
          if t>=10:
            break
          
          for i in range(G.number_of_nodes()):
            x = random.randint(0,2)
            arr2[i] = x
          for j in range (count):
             same = 0
             for k in range (G.number_of_nodes()):
                if arr2[k] == arr[j][k]:
                    same+=1
             if same == G.number_of_nodes():
                control = False
                break
          if control == True:
             t = 0
             for k in range (G.number_of_nodes()):
                arr[count][k] = arr2[k]
             l = 0
             for node in G.nodes:
                G.nodes[node]["Number"] = arr[count][l]
                l+=1
             check = IsFeasible(G)
             if check == True:
                
                finish+=1
                if finish>=100:
                    break
                
                sum2 = weight(G)
                if sum2 < sum:
                    sum = sum2
                    for node in G.nodes:
                      G2.add_node(node,Number = G.nodes[node]["Number"])
                    for edge in G.edges:
                      G2.add_edge(edge[0],edge[1])
             count+=1

          else:
            t+=1
    return G2


def VNS(Rdn,edgeNum,minNodeNum,maxNodeNum):

    max_time = 0
    start_time = time.time()
    G = [None] * 100
    RdnSum = [0] * 100
    G2=nx.Graph()
    G3=nx.Graph()
    X = nx.Graph()


    kmin = 1
    kmax = 30
    kstep = 1
    k = 0
    p = 0.5
    count = 0
    check = True
    check2 = True
    counter = 0

    while counter < 20:
        temp = minNodeNum
        while temp<=maxNodeNum:
            G[count] = nx.Graph()
            for j in range(temp):
                G[count].add_node(chr(ord("A")+j), Number = 0)
            temp+=1
            count+=1
        count = 0
        print("deneme")

        for j in range(edgeNum):
            while True:
                num1 = random.randint(0,minNodeNum-1)
                ch1 = chr(ord("A") + num1)
                num2 = random.randint(0,minNodeNum-1)
                ch2 = chr(ord("A") + num2)

                if ch1 != ch2:
                    for edge in G[0].edges:
                        if ch1 != edge[0] or ch2 != edge[1]:
                           if ch1 != edge[1] or ch2 != edge[0]:
                              check2 = True
                           else:
                              check2 = False
                              break
                        else:
                            check2 = False
                            break
                    if check2 == True:
                        G[0].add_edge(ch1,ch2)
                        break
                    check2 = True


        for i in range(maxNodeNum-minNodeNum):
            for edge in G[i].edges:
                G[i+1].add_edge(edge[0],edge[1])
            

        for j in range(maxNodeNum-minNodeNum+1):
            InitialSolution(G[j])
            sum = weight(G[j])
            while k <= kmax and max_time<=7200:

              k = kmin
            
              while k <= kmax:
                    G2 = nx.Graph()
                    for node in G[j].nodes:
                        G2.add_node(node,Number = G[j].nodes[node]["Number"])
                    
                    for edge in G[j].edges:
                        G2.add_edge(edge[0],edge[1])

                    X = Shake(G2,k,minNodeNum)
                    Y = LocalSearch(X,minNodeNum)
                    check = IsFeasible(Y) 
                    sum2 = weight(Y)

                    if sum2 < sum and check == True:
                            sum = sum2
                            for node2 in Y.nodes:
                                G[j].add_node(node,Number = Y.nodes[node2]["Number"])
                            for edge in Y.edges:
                                G[j].add_edge(edge[0],edge[1])
                            k = kmin
                    else:
                        k += kstep
              max_time = time.time() - start_time
            k = 0
            start_time = time.time()
            RdnSum[j] += weight(G[j])
        counter+=1


    for i in range (maxNodeNum-minNodeNum+1):
        Rdn[i] = RdnSum[i]/20



def BF(Rdn,edgeNum,minNodeNum,maxNodeNum):

    G = [None] * 100
    G2 = [None] * 100
    RdnSum = [0] * 100

    count = 0
    counter = 0
    check2 = True

    while counter < 20:
        temp = minNodeNum
        while temp<=maxNodeNum:
                G[count] = nx.Graph()
                for j in range(temp):
                    G[count].add_node(chr(ord("A")+j))
                temp+=1
                count+=1
        count = 0
        print("deneme2")

        for j in range(edgeNum):
            while True:
                num1 = random.randint(0,minNodeNum-1)
                ch1 = chr(ord("A") + num1)
                num2 = random.randint(0,minNodeNum-1)
                ch2 = chr(ord("A") + num2)

                if ch1 != ch2:
                    for edge in G[0].edges:
                        if ch1 != edge[0] or ch2 != edge[1]:
                           if ch1 != edge[1] or ch2 != edge[0]:
                              check2 = True
                           else:
                              check2 = False
                              break
                        else:
                            check2 = False
                            break
                    if check2 == True:
                        G[0].add_edge(ch1,ch2)
                        break
                    check2 = True


        for i in range(maxNodeNum-minNodeNum):
            for edge in G[i].edges:
                G[i+1].add_edge(edge[0],edge[1])

        for i in range(maxNodeNum-minNodeNum+1):
            G2[i] = nx.Graph()
            G2[i] = BruteForce(G[i])
            RdnSum[i] += weight(G2[i])
            
        counter+=1


    for i in range(maxNodeNum-minNodeNum+1):
        Rdn[i] = RdnSum[i]/20

    

ROOT = tk.Tk()

ROOT.withdraw()

while(True):
 edgeNum = simpledialog.askstring(title="Test",
                                  prompt="Enter edge number:")
 if edgeNum.isdigit():
    edgeNum = int(edgeNum)
    break

while(True):
 minNodeNum = simpledialog.askstring(title="Test",
                                  prompt="Enter minimum vertex number:")

 if minNodeNum.isdigit():
    minNodeNum = int(minNodeNum)
    if (minNodeNum*(minNodeNum-1))/2 >= edgeNum:
       break

while(True):
 maxNodeNum = simpledialog.askstring(title="Test",
                                  prompt="Enter maximum vertex number:")

 if maxNodeNum.isdigit():
    maxNodeNum = int(maxNodeNum)
    if maxNodeNum > minNodeNum:
       break

NodeNum = [0] * 100
Rdn = [0] * 100
NodeNum2 = [0] * 100
Rdn2 = [0] * 100

temp = minNodeNum
count = 0
while temp <= maxNodeNum:
    NodeNum[count] = temp
    NodeNum2[count] = temp
    count += 1
    temp += 1

VNS(Rdn,edgeNum,minNodeNum,maxNodeNum)
BF(Rdn2,edgeNum,minNodeNum,maxNodeNum)

plt.scatter(NodeNum, Rdn, label= "VNS", color= "green", 
                marker= "*", s=30)
plt.scatter(NodeNum2, Rdn2, label= "Brute Force", color= "blue", 
                marker= ".", s=30)

plt.xlabel('Vertex Num')
            # naming the y axis
plt.ylabel('Roman Domination Num')
            
            # giving a title to my graph
plt.title('VNS-Brute Force Comparison')
plt.legend()
plt.show()

