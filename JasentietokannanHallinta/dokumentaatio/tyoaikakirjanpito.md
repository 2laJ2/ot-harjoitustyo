# työaikakirjanpito

| päivä	| aika | mitä tein  |
| :----: | :----- | :----- |
| 14.11. | 0.5	  | annettuihin taustamateriaaleihin tutustuminen | 
|	 | 1      | alustavan määrittelydokumentin luominen |
|        | 0.5    | työn tallentaminen GitHubiin |
| 22.11. | 3      | alustavan netBeans-projektin luominen |
| 23.11. | 9      | JavaFX konfigurointi ja pom.xml-tiedoston muokkaaminen |
|        |        | alustavan harjoitustyön muokkaaminen käynnistyskelpoiseksi referenssisovellusta mukailemalla |
|        | 1      | kahden testin luominen |
|        |        | Jacocon konfigurointi pom.xml-tiedostoon | 
|        | 1      | UserTest testikattavuus 100%, JasentiedotTest 97% |
|        | 1      | dokumentaation päivitystä |
| 29.11. | 1      | Checkstylen käyttöönotto ja checkstyle-virheiden korjausta
|        | 2.5    | käyttönäkymän, uuden käyttäjän luomisnäkymän, jäsentietojen hakunäkymän päivitystä |
|        | 1      | uuden jäsentietojen luomisnäkymän ja jäsentietojen muokkausnäkymän päivitystä |
|        | 0.5    | testauksen päivittäminen |
| 30.11. | 3      | Käyttöliittymän muokkausta; kaikki viisi näkymää näyttävät oikeanlaisilta, linkit näkymien välillä toimivat |
|        | 2      | dokumentaation päivitystä; kuvien siirto omaan kansioon, arkkitehtuuri.md lisäys |  
|        | 1      | dokumentaation päivitystä; vaatimusmaarittely.md, Readme.md |
|        | 1.5    | uuden testin luominen, testaus.md päivittäminen |
| 6.12.  | 3      | kahden uuden linkkinappulan luonti näkymien välille, uusien metodien luominen ja testien kirjoittaminen niille | 
| 7.12.  | 5.5    | lisätty toiminto uuden jäsenen luominen uuden jäsenen luomisnäkymään |
|        |        | lisätty toiminto jäsentietojen hakunäkymän find- toiminto löytää jäsenen (jos jäsen on ensin luotu samalla käyttökerralla) |
|        |        | lisätty tekstikenttien tyhjentyminen linkkiä seurattaessa |
|        |        | muokattu tiedostoa pom.xml suoritettavan jarin generoimiseksi ja varmistettu jarin toimivuus |
|        |        | checktyle-virheiden korjaus |
|        |        | userTest testikattavuus 81%, jasentiedotTest 97%, jasentiedotServiceTest 61% |
|        |        | dokumentaation päivitystä |
|        | 1      | tutustuminen GitHub Releasen tekemisestä annettuihin ohjeisiin ja materiaaleihin |
|        | 1      | Releasen tekeminen, ohjelman lataus- ja käynnistysohjeiden lisäys tiedostoon README.md ja työaikakirjanpidon päivittäminen |
| 13.12. | 5      | vähennetty käyttönäkymiä viidestä kolmeen |
|        |        | poistettu tarpeetonta koodia |
|        |        | lisätty toiminnot jäsentietojen osoitteen ja puhelinnumeron muokkaus |
|        | 3      | tutustuminen Javadociin ja Javadocin lisääminen tiedostoihin FileJasentiedotDao.java, FileUserDao.java, Jasentiedot.java, JasentiedotService.java, User.java |
|        |        | dokumentaation päivitystä; README.md, tyoaikakirjanpito.md |
| 14.12. | 2      | graafisen käyttöliittymän visuaalisen ilmeen hiominen käyttäjäystävällisemmäksi |
|        |        | lisätty toiminto poista jäsen |
|        | 3      | dokumentaation päivitystä vastaamaan käyttöliittymää ja käyttöliittymaluonnoksen päivittäminen |
|        |        | testauksen päivittäminen; uusien testien luonti ja tarpeettomien poisto |
|        |        | userTest testikattavuus 88%, jasentiedotTest 100%, jasentiedotServiceTest 92%, dao 93%, domain 93 % |
|        |        | checkstyle-tarkistus |
|        | 0.5    | Javadocin päivitys |
|        | 4.5    | alustavan arkkitehtuurikuvauksen päivitys, sekvenssikaavion lisäys |
|        |        | alustavan käyttöohjeen lisäys |
|        |        | dokumentaation päivitys |
|        |        | koodin laadun tarkastelu, havaintojen kirjaaminen dokumentaatioon |
|        | 1      | uuden jarin lisäys, GitHub Releasen tekeminen |
| 20.12. | 4      | testauksen päivittäminen; uuden testin FileUserDaoTest lisäys, tarpeettomien testien poisto |
|        |        | käyttämättömien metodien poisto, toisteisen koodin poistaminen testiluokista |
|        |        | testikattavuus domain (JasentiedotService, Jasentiedot, User) 100%, dao (FileUserDao 94%, FileJasentiedotDao 98%) 97%; no missed branches |
|        |        | checkstyle-tarkistus |
|        | 1      | tutustuminen luokkakaavioiden piirto-ohjelmaan sivulla https://app.diagrams.net/ ja käyttöliittymäluonnoksen päivittäminen |
| 22.12. | 3      | pakkausrakenne-, looginen datamalli-, pakkauskaavio- ja kolmen sekvenssikaavioluonnoksen päivittäminen drawio:n avulla |
|        | 4      | users.txt ja jasentiedotList.txt tiedostojen nimet määrittelevän konfiguraatiotiedoston lisääminen |
|        |        | konfiguraatiotiedoston käyttöön vaadittavien java.io.FileInputStream ja java.util.Properties käyttöönotto |
|        |        | jäsentietojen pysyvässä tallennuksessa olleen bugin korjaaminen |
|        |        | dokumentaation päivitystä |
|        |        | testaus.md päivitys |
| 23.12. | 2.5    | uusi toiminto; poistetut jäsentiedot tallennetaan konfiguraatiotiedostossa määritettyyn tiedostoon |
|        |        | uusi ominaisuus; uutta jäsentä luotaessa järjestelmä tarkistaa sekä nykyisistä että poistetuista jäsenistä, mikä on suurin annettu id ja antaa seuraavan numeron |
|        | 1.5    | uusien metodien luominen, checkstyle-tarkistus |
|        | 0.5    | bugin korjaaminen; tallentaa myös kun muokataan olemassaolevia jäsentietoja |
|        | 3      | uusien testien lisääminen, javadocin päivitys |
| 25.12. | 3      | uusien testien lisääminen, checkstyle-tarkistus, testikattavuus |
|        | 2.5    | dokumentaation päivitys, GitHub Releasen tekeminen |
| yht.	 | 84     | |
