# Testausdokumentti

Ohjelmaa on testattu automatisoiduilla yksikkö- ja integraatiotesteillä JUnitilla ja manuaalisesti järjestelmätasolla.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovelluslogiikkaa eli pakkauksen [jasentietokannanhallinta.domain](https://github.com/2laJ2/ot-harjoitustyo/tree/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/domain) luokkia testaavat integraatiotestit

[JasentiedotTest.java](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/test/java/jasentietokannanhallinta/JasentiedotTest.java)

[UserTest.java](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/test/java/jasentietokannanhallinta/UserTest.java)

[JasentiedotServiceTest.java](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/test/java/jasentietokannanhallinta/JasentiedotServiceTest.java)

joiden määrittelemät testitapaukset simuloivat käyttöliittymän [JasentiedotService](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/domain/JasentiedotService.java)-olion avulla suorittamia toiminnallisuuksia. 

Sovelluslogiikkakerroksen luokille [User](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/domain/User.java) ja [Jasentiedot](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/domain/Jasentiedot.java) on tehty muutama yksikkötesti kattamaan tapaukset, joita integraatiotestit eivät kata (mm. olioiden _equals_-metodit).

### DAO-luokat

DAO-luokkaa eli pakkauksen [jasentietokannanhallinta.dao]() testataan _JasentiedotServiceTest_:in lisäksi testillä

[FileUserDaoTest.java](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/test/java/jasentietokannanhallinta/FileUserDaoTest.java) 

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 98% ja haarautumakattavuus 100%.

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/testikattavuus.png" width="750">

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/domain_testikattavuus.png" width="750">

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/dao_testikattavuus.png" width="750">

Testaamatta jäivät tilanteet, joissa käyttäjät tai jäsenet tallettavia tiedostoja ei ole, tai niihin ei ole luku- tai kirjoitusoikeutta.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kayttoohje.md) kuvaamalla tavalla Linux-ympäristössä siten, että sovelluksen käyttöhakemistossa on ollut käyttöohjeen kuvauksen mukainen _config.properties_-tiedosto.

Sovellusta on testattu tilanteessa, jossa käyttäjät ja jäsenet tallettavat tiedostot ovat olleet olemassa ja jossa niitä ei ole ollut, jolloin ohjelma on itse luonut ne.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/vaatimusmaarittely.md#perusversion-tarjoama-toiminnallisuus) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä.
 
## Sovellukseen jääneet laatuongelmat

Sovellus ei anna järkeviä virheilmoituksia, kun konfiguraation määrittelemiin tiedostoihin ei ole luku/kirjoitusoikeuksia.
