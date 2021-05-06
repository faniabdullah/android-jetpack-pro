package com.bangkit.faniabdullah_jetpack.utils

import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.movie.MovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.tvshows.TvShowsResponse

object DataDummy {
    fun generateDummyDataMovieNowPlaying(): List<MovieEntity> {
        val listMovieNowPlaying = ArrayList<MovieEntity>()

        listMovieNowPlaying.add(
            MovieEntity(
                1,
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",

                "2021-03-24",
                8.3,
                8,
            )
        )

        listMovieNowPlaying.add(
            MovieEntity(
                2,
                "/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
                "Chaos Walking",
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",

                "2021-02-24",
                8.3,
                8,
            )
        )


        listMovieNowPlaying.add(
            MovieEntity(
                3,
                "/7prYzufdIOy1KCTZKVWpjBFqqNr.jpg",
                "Raya and the Last Dragon",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",

                "2021-03-03",
                8.3,
                8,
            )
        )

        return listMovieNowPlaying
    }

    fun generateDummyDataTvShowsPopular(): List<TvShowsEntity> {
        val listTvShowsPopular = ArrayList<TvShowsEntity>()
        listTvShowsPopular.add(
            TvShowsEntity(
                111,
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",

                "2021-03-24",
                8.3,
                8,
            )
        )

        listTvShowsPopular.add(
            TvShowsEntity(
                222,
                "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",

                "2017-09-25",
                8.3,
                8,
            )
        )

        listTvShowsPopular.add(
            TvShowsEntity(
                333,
                "/z59kJfcElR9eHO9rJbWp4qWMuee.jp",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",

                "2014-10-07",
                8.3,
                8,
            )
        )

        return listTvShowsPopular
    }

    fun generateDummyDataRemoteNowPlaying(): List<MovieResponse> {
        return listOf(
            MovieResponse(
                adult = false,
                backdropPath = "/jtAI6OJIWLWiRItNSZoWjrsUtmi.jpg",
                genreIds = listOf(10749),
                id = 724089,
                originalLanguage = "en",
                originalTitle = "Gabriel's Inferno Part II",
                overview = "Professor Gabriel Emerson finally learns the truth about Julia Mitchell's identity, but his realization comes a moment too late. Julia is done waiting for the well-respected Dante specialist to remember her and wants nothing more to do with him. Can Gabriel win back her heart before she finds love in another's arms?",
                popularity = 12.613,
                posterPath = "/x5o8cLZfEXMoZczTYWLrUo1P7UJ.jpg",
                releaseDate = "2020-07-31",
                title = "Gabriel's Inferno Part II",
                video = false,
                voteAverage = 8.7,
                voteCount = 1240
            ),
            MovieResponse(
                adult = false,
                backdropPath = "/rSPw7tgCH9c6NqICZef4kZjFOQ5.jpg",
                genreIds = listOf(
                    18,
                    80
                ),
                id = 238,
                originalLanguage = "en",
                originalTitle = "The Godfather",
                overview = "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
                popularity = 45.469,
                posterPath = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
                releaseDate = "1972-03-14",
                title = "The Godfather",
                video = false,
                voteAverage = 8.7,
                voteCount = 14196
            ),
            MovieResponse(
                adult = false,
                backdropPath = "/iNh3BivHyg5sQRPP1KOkzguEX0H.jpg",
                genreIds = listOf(
                    18,
                    80
                ),
                id = 278,
                originalLanguage = "en",
                originalTitle = "The Shawshank Redemption",
                overview = "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
                popularity = 40.104,
                posterPath = "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                releaseDate = "1994-09-23",
                title = "The Shawshank Redemption",
                video = false,
                voteAverage = 8.7,
                voteCount = 18799
            )
        )

    }

    fun generateDummyDataRemoteTvShowPopular(): List<TvShowsResponse> {
        return listOf(
            TvShowsResponse(
                backdropPath = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                firstAirDate = "2021-03-19",
                genreIds = listOf(
                    10765,
                    10759,
                    18,
                    10768
                ),
                id = 88396,
                name = "The Falcon and the Winter Soldier",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "The Falcon and the Winter Soldier",
                overview = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                popularity = 2640.049,
                posterPath = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                voteAverage = 7.9,
                voteCount = 4994
            ),
            TvShowsResponse(
                backdropPath = "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                firstAirDate = "2017-09-25",
                genreIds = listOf(18),
                id = 71712,
                name = "The Good Doctor",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "The Good Doctor",
                overview = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                popularity = 1548.3,
                posterPath = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                voteAverage = 8.6,
                voteCount = 8153
            ),
            TvShowsResponse(
                backdropPath = "/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                firstAirDate = "2016-01-25",
                genreIds = listOf(
                    80,
                    10765
                ),
                id = 63174,
                name = "Lucifer",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "Lucifer",
                overview = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                popularity = 748.564,
                posterPath = "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                voteAverage = 8.5,
                voteCount = 8378
            )
        )
    }

}