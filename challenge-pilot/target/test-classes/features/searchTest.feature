# language: es
# encoding: utf-8

@searchTest
Característica: Test de busqueda en mercado libre

  @searchTest_001 @run
  Escenario: Búsqueda en mercado libre - Visualización del tercer resultado obtenido
    Dado Que el usuario accede a la home de mercado libre
    Cuando Realiza una búsqueda con filtros
    Entonces Visualiza el tercer resultado obtenido en la búsqueda