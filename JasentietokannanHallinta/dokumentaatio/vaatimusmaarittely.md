# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista hallita  urheiluseuran  jäsentietokantaa. Sovellusta voi käyttää useampi rekisteröitynyt käyttäjä, joilla on yksilöllisesti määritelty  pääsy jäsentietoihin. 

## Käyttäjät

Alkuvaiheessa sovelluksella on vain yksi käyttäjärooli, eli pääkäyttäjä. Myöhemmin sovellukseen saatetaan lisätä yksilöllisesti rajoitetuilla oikeuksilla varustettu normaali käyttäjä.

## Käyttöliittymäluonnos

- Sovellus koostuu kolmesta eri näkymästä

<img src="" width="750">

- Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään tai kirjautumisen onnistuessa sovelluksen käyttönäkymään
  - käyttönäkymässä on mahdollista etsiä jäsentietoja tai luoda uusi jäsen
  - valittaessa uuden jäsenen luonti sovellus siirtyy uuden jäsenen luomisnäkymään
  - valittaessa jäsentietojen haku sovellus siirtyy olemassaolevien jäsentietojen käyttönäkymään, jossa on mahdollista muokata tietoja tai palata takaisin muuttamatta tietoja

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- (pää)käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 3 merkkiä

- (pää)käyttäjä voi kirjautua järjestelmään
  - olemassaoleva käyttäjätunnus ja salasana syötetään kirjautumislomakkeelle
  - jos käyttäjätunnusta ei ole olemassa, järjestelmä antaa ilmoituksen

### Kirjautumisen jälkeen

- (pää)käyttäjä näkee seuran jäsentietokantavalikon

- (pää)käyttäjä voi valita haluamansa toiminnon
  - tietokannan muokkaus
  - jäsentietojen haku tietokannasta halutuilla kriteereillä

- myöhemmin lisättävillä normaaleilla käyttäjillä voidaan rajoittaa pääsy vain osaan jäsentietokannan tiedoista 

- käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavanlaisilla toiminnoilla:

- pääkäyttäjä voi luoda järjestelmään uuden  käyttäjätunnuksen yksilöllisine salasanoineen (normaali käyttäjä)
  - pääkäyttäjä voi määritellä yksilöllisesti uuden käyttäjän pääsyn eri jäsentietoihin, esim. toimistosihteerillä hyvin rajattu pääsy
  - pääkäyttäjä voi lisäksi määritellä erilaisia käyttäjätiimejä seuran tarpeiden mukaan, esim. yhdistyksen hallitus
  - pääkäyttäjä voi poistaa käyttäjätunnuksen tai vaihtaa käyttäjätunnuksen salasanan

- voidaan lisätä erilaisia jäsentietojen hakukriteerejä tarpeen mukaan

- voidaan poistaa tarpeettomia jäsentietojen hakukriteerejä

- voidaan järjestellä jäsentietojen haku haluttujen kriteerien mukaan, esim.
  - maksetut jäsen- ja harjoitusmaksut
  - urheilutapahtumiin ja -kilpailuihin osallistuminen
  - seuran omiin tapahtumiin ja tehtäviin osallistuminen
  - sijoittuminen urheilutapahtumissa ja -kilpailuissa
  - ranking-listan mukainen haku (esim. kamppailulajiseurassa vyöarvon mukaan)

- voidaan luoda kullekin käyttäjälle sovelluksen käytön lokitiedosto, johon vain pääkäyttäjällä on pääsy

