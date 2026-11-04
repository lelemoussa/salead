// 1. Closure estChere (prix > 100 000)
def estChere = { Map annonce -> annonce.prix > 100000 }
assert estChere([prix: 120000]) == true
assert estChere([prix: 85000]) == false

// 2. Closure avecTva (prix augmenté de 18%)
def avecTva = { Number prix -> prix * 1.18 }
assert avecTva(10000) == 11800.0

// 3. Closure formater (deux paramètres : titre et prix)
def formater = { String titre, Number prix -> "$titre -> $prix FCFA" }
assert formater('Téléphone', 120000) == "Téléphone -> 120000 FCFA"

// 4. Méthode appliquer(List liste, Closure c)
def appliquer(List liste, Closure c) {
    return liste.collect(c)
}

def nombres = [10000, 50000]
assert appliquer(nombres, avecTva) == [11800.0, 59000.0]

println "Étape 2 validée avec succès !"