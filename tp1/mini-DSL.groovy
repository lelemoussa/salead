class SaleAd {
    String titre
    Number prix
    String ville
    String statut

    boolean estValide() {
        return titre != null && !titre.trim().isEmpty() && prix != null && prix > 0
    }
}

// 1. Instanciation par constructeur à arguments nommés
def ad1 = new SaleAd(titre: 'Vélo tout terrain', prix: 85000, ville: 'Abidjan', statut: 'ACTIVE')
def adInvalide = new SaleAd(titre: '', prix: -500, ville: 'Abidjan', statut: 'ACTIVE')

assert ad1.estValide() == true
assert adInvalide.estValide() == false

// 2. Mini-DSL : publier annonce a 'Abidjan'
def a(String ville) {
    return ville
}

def publier(SaleAd annonce) {
    [a: { String ville ->
        annonce.ville = ville
        annonce.statut = 'ACTIVE'
        return annonce
    }]
}

// Test de la syntaxe DSL sans parenthèses
def nouvelleAnnonce = new SaleAd(titre: 'Canapé 3 places', prix: 150000)
publier nouvelleAnnonce a 'Abidjan'

assert nouvelleAnnonce.ville == 'Abidjan'
assert nouvelleAnnonce.statut == 'ACTIVE'

println "Étape 4 validée avec succès !"