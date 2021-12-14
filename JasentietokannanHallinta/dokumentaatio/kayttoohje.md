# Käyttöohje

Lataa tiedosto [JasentietokannanHallinta-1.0-SNAPSHOT.jar](https://github.com/2laJ2/ot-harjoitustyo/releases).

## konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on käyytäjät ja jäsentiedot tallettavat tiedostot _users.txt_ ja _jasentiedotList.txt_.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

```
java -jar JasentietokannanHallinta-1.0-SNAPSHOT.jar

```
## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään.

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/kayttoohje1.png" width="450">

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus ja salasana syötekenttiin ja klikkaamalla painiketta _login_.

## Uuden käyttäjän luominen

Kirjautumisnäkymästä on mahdollista siirtyä uuden käyttäjän luomisnäkymään klikkaamalla painiketta _create new user_.

Uusi käyttäjä luodaan syöttämällä tiedot syötekenttiin ja klikkaamalla painiketta _create_.

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/kayttoohje2.png" width="450">

Jos käyttäjän luominen onnistuu, sovellus palaa takaisin kirjautumisnäkymään.

Vaihtoehtoisesti luomisnäkymästä on mahdollista siirtyä takaisin kirjautumisnäkymään ilman uuden käyttäjän luomista klikkaamalla painiketta _back_.

## Jäsentietojen luominen, etsiminen, muokkaaminen ja poisto

Onnistuneen kirjautumisen jälkeen siirrytään sovelluksen varsinaiseen päänäkymään.

Näkymässä on mahdollista luoda uusia jäsentietoja kirjoittamalla syötekenttiin jäsentiedot ja klikkamaalla painiketta _create new member_. Jos uuden jäsenen luominen onnistuu, näkymän yläosaan ilmestyy vihreä teksti _new member created_.

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/kayttoohje3.png" width="450">

Luotu jäsentieto on mahdollista etsiä kirjoittamalla syötekenttään jäsenen nimi ja klikkaamalla painiketta _find_. Jos annetulla nimellä löytyy olemassaoleva jäsen, jäsentiedot tulevat näkyviin näkymän syötekenttin.

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/kayttoohje4.png" width="450">

Jos annetulla nimellä ei löydy jäsentietoja, näkymän yläosaan ilmestyy punainen teksti _member not found_.

Luotuja jäsentietoja on mahdollista muokata. Tämä tapahtuu kirjoittamalla jäsenen nimi syötekenttään ja klikkaamalla painiketta _find_, minkä jälkeen syötekenttiin näkyviin tulevia osoite- ja puhelinnumerotietoja on mahdollista muokata ja tämän jälkeen tallentaa klikkaamalla painiketta -edit_.

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/kayttoohje5.png" width="450">

Jos halutaan muuttaa olemassaolevan jäsenen nimi, tämä tehdään siten, että ensin etsitään jäsenen tiedot vanhalla nimellä. Tämän jälkeen syötekenttään näkyviin tuleva vanha nimi muutetaan ja jäsen tallennetaan kokonaan uutena jäsenenä klikkaamalla painiketta _create new member_. Jos uuden jäsenen luominen onnistuu, näkymän yläosaan ilmestyy vihreä teksti _new member created_.
Tämän jälkeen etsitään vanhat jäsentiedot kirjoittamalla syötekenttään vanha nimi ja klikkaamalla painiketta _find_, jolloin vanhat jäsentiedot tulevat näkyviin näkymän syötekenttiin. Seuraavaksi klikataan painiketta _delete member_, jossa on punainen teksti. Jos jäsenen poistaminen onnistuu, näkymän yläosaan ilmestyy punainen teksti _member deleted_.
Tämän jälkeen vanhalla nimellä tallennetut jäsentiedot on pysyvästi poistettu ja uudella nimellä tallennetut jäsentiedot löytyvät kirjoittamalla syötekenttään uusi nimi ja klikkaamalla painiketta _find_.

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/kayttoohje6.png" width="450">

Klikkaamalla näkymän yläreunan painiketta _logout_ käyttäjä kirjautuu ulos sovelluksesta ja sovellus palaa takaisin kirjautumisnäkymään.


