import random

def factorize(n):

    factors = []
    d = 2

    while n > 1:
        # Try to divide by small primes using trial division
        while n % d == 0:
            factors.append(d)
            n //= d

        # If n is still not 1, use Pollard's Rho algorithm
        if n > 1:
            x = random.randint(2, n-1)
            y = x
            c = random.randint(1, n-1)
            g = 1

            while g == 1:
                x = (x*x + c) % n
                y = (y*y + c) % n
                y = (y*y + c) % n
                g = gcd(abs(x-y), n)
                #print("g",g)

            # If Pollard's Rho found a non-trivial factor, add it to the list of factors
            if g != n:
                factors += factorize(g)
                factors += factorize(n//g)
                break

            # If Pollard's Rho failed, increment d and try trial division again
            else:
                d += 1

    return sorted(factors)

def gcd(a, b):
    if b == 0:
        return a
    else:
        return gcd(b, a % b)


factors = factorize(390)
print(factors)
