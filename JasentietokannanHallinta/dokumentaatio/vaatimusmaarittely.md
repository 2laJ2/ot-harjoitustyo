# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista hallita  urheiluseuran  jäsentietokantaa. Sovellusta voi käyttää useampi rekisteröitynyt käyttäjä, joilla on yksilöllisesti määritelty  pääsy jäsentietoihin. 

## Käyttäjät

Alkuvaiheessa sovelluksella on vain yksi käyttäjärooli, eli pääkäyttäjä. Myöhemmin sovellukseen saatetaan lisätä yksilöllisesti rajoitetuilla oikeuksilla varustettu normaali käyttäjä. "tehty"

## Käyttöliittymäluonnos

- Sovellus koostuu viidestä eri näkymästä: 

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/kayttoliittymaluonnos.jpg" width="750">

- Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään tai kirjautumisen onnistuessa sovelluksen käyttönäkymään
  - käyttönäkymässä on mahdollista etsiä jäsentietoja tai luoda uusi jäsen 
  - valittaessa uuden jäsenen luonti sovellus siirtyy uuden jäsenen luomisnäkymään
  - valittaessa jäsentietojen haku sovellus siirtyy olemassaolevien jäsentietojen käyttönäkymään, jossa on mahdollista muokata tietoja tai palata takaisin muuttamatta tietoja

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- (pää)käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 3 merkkiä 

- (pää)käyttäjä voi kirjautua järjestelmään
  - olemassaoleva käyttäjätunnus ja salasana syötetään kirjautumislomakkeelle (järjestelmä ei vielä toistaiseksi tarkista, onko syötetty salasana oikein)
  - jos käyttäjätunnusta ei ole olemassa, järjestelmä antaa ilmoituksen

### Kirjautumisen jälkeen

- (pää)käyttäjä näkee seuran jäsentietokantavalikon / jäsentietojen hakunäkymän

- (pää)käyttäjä voi valita haluamansa toiminnon
  - tietokannan muokkaus, jolloin (pää)käyttäjä siirtyy uuden jäsenen luontinäkymään
  - jäsentietojen haku tietokannasta halutuilla kriteereillä, jolloin (pää)käyttäjä siirtyy jäsentietojen tarkastelu / muokkausnäkymään
    - järjestelmä löytää samalla käyttökerralla järjestelmään tallennetun jäsenen annetun nimen ollessa oikea (toistaiseksi järjestelmä ei vielä tallenna jäseniä seuraavaa käyttökertaa varten) "tehty"    

- (pää)käyttäjä voi siirtyä uuden jäsen luontinäkymästä ja jäsentietojen tarkastelu / muokkausnäkymästä takaisin jäsentietokantavalikkoon
  - uuden jäsenen luontinäkymässä voidaan luoda uusi jäsen "tehty"
    - jäsenestä tallennetaan jäsentietoihin nimi, osoite ja puhelinnumero "tehty"
  - uuden jäsenen luontinäkymästä voi siirtyä takaisin jäsentietojen hakunäkymään joko luomalla uusi jäsen tai valitsemalla paluutoiminto ilman uuden jäsenen luontia "tehty"
  - jäsentietojen tarkastelunäkymässä voidaan muokata olemassaolevia jäsentietoja (toimintoa jäsentietojen muokkaus ei ole vielä lisätty, eikä näkymä vielä toistaiseksi näytä löydetyn jäsenen tietoja)
  - jäsentietojen tarkastelunäkymästä voi siirtyä takaisin jäsentietojen hakunäkymään valitsemalla jäsentietojen muokkaus (muokkaustoimintoa ei vielä lisätty) tai paluutoiminto ilman muokkausta "tehty"

- siirryttäessä näkymästä toiseen järjestelmä tyhjentää tekstikentät, jolloin siirryttäessä samaan näkymään uudelleen näkymässä ei ole edellisen käyttökerran tietoja "tehty"

- myöhemmin lisättävillä normaaleilla käyttäjillä voidaan rajoittaa pääsy vain osaan jäsentietokannan tiedoista 

- käyttäjä voi kirjautua ulos järjestelmästä
- seuraavalla käyttökerralla järjestelmä muistaa käyttäjän, käyttäjä voi suoraan kirjautua järjestelmään

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavanlaisilla toiminnoilla:

- pääkäyttäjä voi luoda järjestelmään uuden  käyttäjätunnuksen yksilöllisine salasanoineen (normaali käyttäjä) (mutta järjestelmä ei vielä toistaiseksi tarkista onko salasana oikea)
  - salasanan täytyy olla uniikki ja pituudeltaan vähintään 10 merkkiä
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

