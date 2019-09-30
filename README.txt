Adapter:
MovieAdapter y SerieAdapter (Adaptadores de vistas por lista de peliculas y series, ClickListener a Detalles de Peliculas)

Models:
RMoviesServiceData y RMoviesServiceData (Modelos de Peliculas y series (Por pagina) con listas de Movies y Series)
RMovie y RSerie (Modelos del Movies y Series)
VideoServiceData y RVideo (Modelos de parametros de videos por Pelicula o Serie)

Network:
RetrofitClient (Definicion de instancias de retrofit y manejo de Cache)
VideoAPI (Obtiene Video Data)
MovieAPI (Obtiene Peliculas Data - Popular, Top Rated, Upcoming - )
SeriesAPI (Obtiene Series Data - Popular, Top Rated, Airing Today (No mostrado) - )

Splash:
SplashActivity (carga inicial de datos de peliculas)

UI:
MainActivity (Muestra los listados de peliculas y administra si mostrar peliculas o series)
DetailActivity (Muestra el detalle de peliculas(o series) y muestra los videos)


Preguntas:

1. En qué consiste el principio de responsabilidad única? Cuál es su propósito? 
Consiste en que cada objeto debe tener una unica tarea dentro de las funcionalidades de la aplicacion


2. Qué características tiene, según su opinión, un “buen” código o código limpio? 
  Un codigo limpio debe respetar una arquitectura, tambien el concepto de responsabilidad unica.
tener un orden y organizacion de los elementos que lo componen. 

