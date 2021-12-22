# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista hallita  urheiluseuran  jäsentietokantaa. Sovellusta voi käyttää useampi rekisteröitynyt käyttäjä, joilla on yksilöllisesti määritelty  pääsy jäsentietoihin. 

## Käyttäjät

Alkuvaiheessa sovelluksella on vain yksi käyttäjärooli, eli pääkäyttäjä. Myöhemmin sovellukseen saatetaan lisätä yksilöllisesti rajoitetuilla oikeuksilla varustettu normaali käyttäjä. 

## Käyttöliittymäluonnos

- Sovellus koostuu kolmesta eri näkymästä: 

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/kayttoliittymaluonnos.png" width="500">

- Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään tai kirjautumisen onnistuessa sovelluksen käyttönäkymään
  - käyttönäkymässä on mahdollista etsiä jäsentietoja tai luoda uusi jäsen 
  - käyttöliittymässä on mahdollista poistaa jäsen
  - käyttöliittymässä on mahdollista muuttaa olemassaolevan jäsenen osoite- ja puhelinnumerotietoja

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- (pää)käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 3 merkkiä 

- (pää)käyttäjä voi kirjautua järjestelmään
  - olemassaoleva käyttäjätunnus ja salasana syötetään kirjautumislomakkeelle
  - jos käyttäjätunnusta ei ole olemassa, järjestelmä antaa ilmoituksen
  - kirjautuminen onnistuu, jos syötetty salasana on oikea
  - jos syötetty salasana on väärä, järjestelmä antaa ilmoituksen

### Kirjautumisen jälkeen

- (pää)käyttäjä näkee seuran jäsentietokantavalikon / jäsentietojen hakunäkymän

- (pää)käyttäjä voi valita haluamansa toiminnon
  - jäsentietojen haku tietokannasta halutuilla kriteereillä, jolloin järjestelmä näyttää jäsenen nimi-, osoite- ja puhelinnumerotiedot 
    - järjestelmä löytää samalla käyttökerralla järjestelmään tallennetun jäsenen annetun nimen ollessa oikea (toistaiseksi järjestelmä ei vielä tallenna jäseniä seuraavaa käyttökertaa varten)    
  - uuden jäsenen luominen 
    - jäsenestä tallennetaan jäsentietoihin nimi, osoite ja puhelinnumero
  - jäsentietojen muokkaus
    - aiemmin luodun jäsenen osoite- ja puhelinnumerotiedon voi vaihtaa, jolloin ohjelma ei tarkasta, täyttävätkö osoite- ja puhelinnumerotiedot vähimmäispituusvaatimuksen
      (tämä on hyödyllinen ominaisuus siltä varalta, että jäsenen todelliset osoite- ja puhelinnumerotiedot eivät täytä annettuja kriteerejä)
    - nimeä ei voi vaihtaa muokkaustoiminnolla, jotta vältytään tilanteelta, missä on tehty kirjoitusvirhe ja tallennettu virheelliset tiedot

  - jäsenen poistaminen
    - toiminto poistaa pysyvästi jäsenen, jonka nimi on jäsentietokentässä (ei jäsentietojen haku -kentässä)
      (tämä on tarpeellinen toiminto tilanteessa, missä halutaan vaihtaa jäsenen nimi - ensin luodaan uudella nimellä kokonaan uusi jäsen, minkä jälkeen poistetaan vanhalla nimellä luotu jäsen)

- siirryttäessä näkymästä toiseen järjestelmä tyhjentää tekstikentät, jolloin siirryttäessä samaan näkymään uudelleen näkymässä ei ole edellisen käyttökerran tietoja / ilmoituksia

- myöhemmin lisättävillä normaaleilla käyttäjillä voidaan rajoittaa pääsy vain osaan jäsentietokannan tiedoista 

- käyttäjä voi kirjautua ulos järjestelmästä
- seuraavalla käyttökerralla järjestelmä muistaa käyttäjän, käyttäjä voi kirjautua järjestelmään antamalla käyttäjätunnuksen ja salasanan, jonka oikeellisuuden järjestelmä tarkistaa

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

- voidaan luoda jäsenistöstä halutuilla kriteereillä ToDo-lista esim. jäsenkirjeen lähetystä varten
  - voidaan tallentaa ToDo-lista erilliseen tiedostoon halutulla nimellä myöhempää tarvetta varten

- voidaan luoda kullekin käyttäjälle sovelluksen käytön lokitiedosto, johon vain pääkäyttäjällä on pääsy
