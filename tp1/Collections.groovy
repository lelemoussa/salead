def annonces = [
    [titre: 'Vélo tout terrain', prix: 85000, ville: 'Abidjan', statut: 'ACTIVE'],
    [titre: 'Téléphone',        prix: 120000, ville: 'Bouaké', statut: 'VENDUE'],
    [titre: 'Table en bois',     prix: 45000, ville: 'Abidjan', statut: 'ACTIVE'],
    [titre: 'Ordinateur',        prix: 250000, ville: 'Daloa', statut: 'ACTIVE'],
    [titre: 'Machine à coudre', prix: 60000, ville: 'Bouaké', statut: 'BROUILLON']
]

// 1. Titres des annonces actives
def titresActifs1 = annonces.findAll { it.statut == 'ACTIVE' }.collect { it.titre }
def titresActifs2 = annonces.findAll { it.statut == 'ACTIVE' }*.titre // opérateur spread *.
assert titresActifs1 == ['Vélo tout terrain', 'Table en bois', 'Ordinateur']
assert titresActifs2 == ['Vélo tout terrain', 'Table en bois', 'Ordinateur']

// 2. Première annonce de Bouaké & recherche à Man
def annonceBouake = annonces.find { it.ville == 'Bouaké' }
assert annonceBouake.titre == 'Téléphone'

def annonceMan = annonces.find { it.ville == 'Man' }
assert annonceMan == null

// 3. Prix total et prix moyen des annonces actives
def annoncesActives = annonces.findAll { it.statut == 'ACTIVE' }
def prixTotal = annoncesActives.sum { it.prix }
def prixMoyen = prixTotal / annoncesActives.size()

assert prixTotal == 380000
assert prixMoyen == 126666.66666666667 // ou (380000 / 3)

// 4. Regroupement (groupBy) et comptage (countBy) par ville
def parVille = annonces.groupBy { it.ville }
assert parVille['Abidjan'].size() == 2
assert parVille['Bouaké'].size() == 2

def nbParVille = annonces.countBy { it.ville }
assert nbParVille == [Abidjan: 2, Bouaké: 2, Daloa: 1]

// 5. Liste triée par prix décroissant
def trieeParPrixDesc = annonces.sort(false) { a, b -> b.prix <=> a.prix }
assert trieeParPrixDesc*.prix == [250000, 120000, 85000, 60000, 45000]

// 6. Table associative titre -> prix (collectEntries)
def mapTitrePrix = annonces.collectEntries { [it.titre, it.prix] }
assert mapTitrePrix['Ordinateur'] == 250000
assert mapTitrePrix['Table en bois'] == 45000

// 7. Séparation en deux listes (< 100 000 et les autres)
def (petitsPrix, grandsPrix) = annonces.split { it.prix < 100000 }
assert petitsPrix*.titre == ['Vélo tout terrain', 'Table en bois', 'Machine à coudre']
assert grandsPrix*.titre == ['Téléphone', 'Ordinateur']

println "Étape 3 validée avec succès !"