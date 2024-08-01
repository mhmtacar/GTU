def trial_division(n: int) :
    a = []              
    f = 2                  
    while n > 1:        
        if n % f == 0:          
            a.append(f)  
            n //= f       
        else:            
            f += 1       
    return a 
num = int(input("Enter number: "))
a = trial_division(num)
print("Prime factorization of number " + str(num) + ": " + str(a)  )






