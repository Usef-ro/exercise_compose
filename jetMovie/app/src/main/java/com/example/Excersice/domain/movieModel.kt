package com.example.Excersice.domain




data class movieModel(
//    @SerializedName("Actors")
    val actors: String,
//    @SerializedName("Awards")
    val awards: String,
//    @SerializedName("Country")
    val country: String,
//    @SerializedName("Director")
    val director: String,
//    @SerializedName("Genre")
    val genre: String,

//    @SerializedName("imdbID")
    val imdbID: String,
//    @SerializedName("imdbRating")
    val imdbRating: String,
//    @SerializedName("imdbVotes")
    val imdbVotes: String,
//    @SerializedName("Language")
    val language: String,
//    @SerializedName("Metascore")
    val metascore: String,
//    @SerializedName("Plot")
    val plot: String,
//    @SerializedName("Poster")
    val poster: String,
//    @SerializedName("Rated")
    val rated: String,
//    @SerializedName("Released")
    val released: String,
//    @SerializedName("Response")
    val response: String,
//    @SerializedName("Runtime")
    val runtime: String,
//    @SerializedName("Title")
    val title: String,
//    @SerializedName("Type")
    val type: String,
//    @SerializedName("Writer")
    val writer: String,
//    @SerializedName("Year")
    val year: String,
    val images: List<String>
)

    fun getMovie(): List<movieModel> {

        val movies = listOf<movieModel>(
            movieModel(
                "Avatar",
                "2009",
                "PG-13",
                "18 Dec 2009",
                "162 min",

                "James Cameron",
                "James Cameron",
                "Sam Worthington, Zoe Saldana, Sigourney Weaver, Stephen Lang",
                "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
                "English, Spanish",
                "USA, UK",
                "Won 3 Oscars. Another 80 wins & 121 nominations.",
                "http://ia.media-imdb.com/images/M/MV5BMTYwOTEwNjAzMl5BMl5BanBnXkFtZTcwODc5MTUwMw@@._V1_SX300.jpg",
                "83",
                "7.9",
                "890,617",
                "tt0499549",
                "movie",
                "True",
                "1990",
               images= listOf(  "https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyOTYyMzUxNl5BMl5BanBnXkFtZTcwNTg0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTY2ODQ3NjMyMl5BMl5BanBnXkFtZTcwODg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTMxOTEwNDcxN15BMl5BanBnXkFtZTcwOTg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTYxMDg1Nzk1MV5BMl5BanBnXkFtZTcwMDk0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg"
                )
            ),
            movieModel(
                "Avatar",
                "2009",
                "PG-13",
                "18 Dec 2009",
                "162 min",

                "James Cameron",
                "James Cameron",
                "Sam Worthington, Zoe Saldana, Sigourney Weaver, Stephen Lang",
                "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
                "English, Spanish",
                "USA, UK",
                "Won 3 Oscars. Another 80 wins & 121 nominations.",
                "http://ia.media-imdb.com/images/M/MV5BMTYwOTEwNjAzMl5BMl5BanBnXkFtZTcwODc5MTUwMw@@._V1_SX300.jpg",
                "83",
                "7.9",
                "890,617",
                "tt0499549",
                "movie",
                "True",
                "1990",
               images= listOf(
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyOTYyMzUxNl5BMl5BanBnXkFtZTcwNTg0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTY2ODQ3NjMyMl5BMl5BanBnXkFtZTcwODg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTMxOTEwNDcxN15BMl5BanBnXkFtZTcwOTg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTYxMDg1Nzk1MV5BMl5BanBnXkFtZTcwMDk0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg"
                )
            ),
        )



        return movies
    }
