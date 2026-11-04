def description = "Contactez le vendeur au 0701020304 ou au 0102030405 pour plus d'infos."

// Extrait tous les numéros à 10 chiffres (opérateur =~ matcher)
def numerosTrouves = (description =~ /\b\d{10}\b/).findAll()

assert numerosTrouves == ['0701020304', '0102030405']

// Vérification stricte du format 10 chiffres (opérateur ==~ exact match)
def regexFormat = /\d{10}/
assert '0701020304' ==~ regexFormat
assert '0701020' ==~ regexFormat == false

println "Étape 5 validée avec succès !"