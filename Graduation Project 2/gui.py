import tkinter as tk
import random
import math

arayüz = tk.Tk()
arayüz.title("Integer Factorization")
arayüz.geometry("400x200")

def factorize(n):
    factors = []

    # Trial division to quickly remove small prime factors
    d = 2
    while d * d <= n:
        if n % d == 0:
            factors.append(d)
            n //= d
        else:
            d += 1

    # If n is a prime number
    if n > 1:
        factors.append(n)

    return factors

def pollard_rho(n):
    if n == 1:
        return []

    def f(x):
        return (x * x + 1) % n

    factors = []

    while True:
        x = random.randint(2, n - 1)
        y = x
        d = 1

        while d == 1:
            x = f(x)
            y = f(f(y))
            d = math.gcd(abs(x - y), n)

        if d != n:
            factors.extend(factorize(d))
            factors.extend(factorize(n // d))
            break

    return factors

def factorize_large_number():
    if y.get().isdigit():
        kullan = int(y.get())
        if kullan<=1:
            result.config(text="Please enter an integer value greater than 1", fg="red")
        else:
            factors = pollard_rho(kullan)
            result.config(text=str(factors), fg="red")
    else:
        result.config(text="You entered the wrong type. Please enter an integer", fg="red")

kullanici = tk.Label(justify = tk.CENTER, text="Enter an integer:")
kullanici.place(x=20, y=10)

y = tk.StringVar()
kullanici_girisi = tk.Entry(justify= tk.CENTER, textvariable=y)
kullanici_girisi.place(x=130, y=10)

giris = tk.Button(text="Enter", command=factorize_large_number)
giris.place(x=150, y=55)

result = tk.Label(text="", font="Verdana 10 bold")
result.place(x=100, y=95)

arayüz.mainloop()
