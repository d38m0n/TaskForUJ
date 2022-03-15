System Identity Management w formie API z wbudowanym interfejsem
Swagger UI
Zarządzanie i budowa projektu: Maven
Wersja Java: 11
Frameworki do zastosowania: Spring Boot, Swagger UI

Krótki opis projektu
Celem projektu jest zbudowanie niewielkiego API pełniącego rolę systemu Identity Management.
System powinien pozwalać na:
 zarządzanie bazą użytkowników
 zarządzanie listą serwisów (serwisy to różne strony/aplikacje www reprezentowane przez
adres URL serwisu)
 weryfikację tożsamości użytkowników umożliwiającą serwisom zewnętrznym rozpoznanie
użytkownika (z użyciem loginu i hasła)
 autoryzację użytkownika w różnych systemach (potwierdzenie czy użytkownik ma
uprawnienia do dostępu danego serwisu)
Specyfikacja punktów końcowych w podziale na ich rodzaj
1. Punkty do zarządzania IDM, nie wymagamy by były zabezpieczone (tzn. nie trzeba podawać
żadnych poświadczeń czy haseł by się do nich dostać, w rozwiązaniu produkcyjnym oczywiście takie
zabezpieczenia musiałyby istnieć), powinny być obsługiwane przez Swagger UI:
 serwis-dodaj
 serwis-modyfikuj
 serwis-usun
 serwis-lista
 uzytkownik-dodaj - umożliwia dodanie do systemu nowego użytkownika
 uzytkownik-info - zwraca informację o użytkowniku
 uzytkownik-modyfikuj - umożliwia zmianę danych istniejącego użytkownika
 uzytkownik-lista - zwraca listę użytkowników
 uzytkownik-przyznaj-dostepu-do-serwisu - przydziela uprawnienia do serwisu
 uzytkownik-odbierz-dostepu-do-serwisu- odbiera uprawnienia
 uzytkownik-uprawnienia - zwraca listę serwisów, do których użytkownik ma uprawnienia
 uzytkownik-zmiana-hasla - pozwala na zmianę hasła użytkownika, wymagane jest podanie
aktualnego hasła
 uzytkownik-zablokuj - blokuje użytkownika (uniemożliwia uzyskiwanie autentykacji)
 uzytkownik-autentykacje - zwraca listę z informacjami kiedy użytkownik uzyskiwał
autentykację
2. Punkty dostępne dla serwisów zewnętrznych, są dostępne publicznie:
 autentykuj - autentykuje użytkownika na podstawie loginu i hasła, zwraca zewnętrznemu
serwisowi informacje czy operacja się udała
 autoryzuj – sprawdza czy użytkownik ma uprawnienia do danego wskazanego serwisu
Punkty powinny odbierać i wysłać dane w formacie JSON.
Przykład użycia:
GET http://loclalhost:8080/uzytkownik-lista
Przykład odpowiedzi:
[
{id=1, nazwa='Marek'},
{id=2, nazwa='Adam'}
] 
