package com.sorsix.backend.api.dummy

import com.sorsix.backend.api.requests.ComponentRatingRequest
import com.sorsix.backend.api.requests.OfferRequest
import com.sorsix.backend.api.requests.ReviewRequest
import com.sorsix.backend.domain.entities.PropertyImages
import com.sorsix.backend.domain.entities.UserImage
import com.sorsix.backend.domain.enums.BookingStatusEnum
import com.sorsix.backend.encoder
import com.sorsix.backend.repository.booking.BookingRepository
import com.sorsix.backend.repository.booking.status.BookingStatusRepository
import com.sorsix.backend.repository.property.availabilities.PropertyAvailabilityRepository
import com.sorsix.backend.repository.property.images.PropertyImagesRepository
import com.sorsix.backend.repository.property.repository.PropertyRepository
import com.sorsix.backend.repository.review.component.ReviewComponentRepository
import com.sorsix.backend.repository.users.UserAccountRepository
import com.sorsix.backend.repository.users.image.UserImageRepository
import com.sorsix.backend.service.PropertyService
import com.sorsix.backend.service.ReviewService
import com.sorsix.backend.service.exceptions.BookingStatusNotFoundException
import org.springframework.stereotype.Service
import java.io.File
import kotlin.random.Random

@Service
class DummyDataPopulator(
    private val userAccountRepository: UserAccountRepository,
    private val userImageRepository: UserImageRepository,
    private val propertyRepository: PropertyRepository,
    private val propertyImagesRepository: PropertyImagesRepository,
    private val bookingRepository: BookingRepository,
    private val bookingStatusRepository: BookingStatusRepository,
    private val propertyService: PropertyService,
    private val propertyAvailabilityRepository: PropertyAvailabilityRepository,
    private val reviewService: ReviewService,
    private val reviewComponentRepository: ReviewComponentRepository,
) {
    private val comments =
        mapOf(
            1 to
                "My recent experience at this accommodation left me feeling deeply disappointed and frustrated. " +
                "From start to finish, it was marred by a series of unfortunate events that overshadowed any " +
                "positive aspects.\n" +
                "\n" +
                "To begin with, the location of the accommodation was far from ideal. Situated in a noisy and congested" +
                " area, it offered little respite from the chaos of the surrounding environment. The incessant noise" +
                " made it difficult to relax and enjoy my time there, turning what should have been a peaceful" +
                " getaway into a stressful ordeal.\n" +
                "\n" +
                "The condition of the property itself was appalling. Upon arrival, I was greeted by unkempt surroundings " +
                "and a distinct lack of cleanliness. The room was in a state of disrepair, with stains on the carpet" +
                ", peeling wallpaper, and a musty odor that permeated the air. It was clear that little effort had" +
                " been made to maintain or refurbish the property, resulting in a deeply unpleasant living " +
                "environment.\n" +
                "\n" +
                "Adding to my frustration were the numerous amenities that were either non-functional or subpar." +
                " The Wi-Fi was unreliable, the shower had low water pressure, and the bedding was old and " +
                "uncomfortable. It felt as though every aspect of the accommodation had been neglected, " +
                "leaving guests to suffer through a substandard experience.\n" +
                "\n" +
                "To make matters worse, the customer service was abysmal. The staff were unresponsive to my " +
                "concerns and seemed indifferent to the issues I raised during my stay. It was clear that " +
                "they had little regard for the well-being or satisfaction of their guests, which only served" +
                " to exacerbate my frustration and disappointment.\n" +
                "\n" +
                "In conclusion, my experience at this accommodation was nothing short of a nightmare. It failed to " +
                "meet even the most basic standards of cleanliness, comfort, and hospitality, leaving me feeling" +
                " angry and disheartened. I would strongly advise against booking a stay here, as it is unlikely" +
                " to result in anything other than disappointment and regret.",
            2 to
                "My recent stay at this accommodation was unfortunately below average, leaving much to be desired in " +
                "terms of both amenities and overall experience.\n" +
                "\n" +
                "Starting with the positives, the location of the accommodation was relatively convenient, offering " +
                "easy access to nearby attractions and amenities. However, this was one of the few redeeming" +
                " qualities of my stay.\n" +
                "\n" +
                "Upon arrival, I was immediately struck by the lackluster condition of the property. The room was " +
                "clean but outdated, with worn furniture and tired decor that gave the impression of neglect. " +
                "It was evident that little effort had been made to maintain or modernize the accommodations, " +
                "resulting in a rather uninspiring living environment.\n" +
                "\n" +
                "In terms of amenities, the accommodation fell short of expectations. While the basics were covered," +
                " such as a functioning shower and comfortable bedding, there were notable deficiencies in other " +
                "areas. The Wi-Fi was slow and unreliable, making it difficult to stay connected, and the lack of " +
                "air conditioning made for uncomfortable nights during warmer weather.\n" +
                "\n" +
                "Furthermore, the customer service left much to be desired. The staff were polite but unresponsive to" +
                " my concerns, showing little initiative in addressing the issues I encountered during my stay. " +
                "It was disappointing to see such a lack of attention to guest satisfaction, particularly given " +
                "the price point of the accommodation.\n" +
                "\n" +
                "In conclusion, while this accommodation may suffice for travelers seeking a budget-friendly option," +
                " it falls short in terms of both quality and service. I would recommend exploring other options " +
                "in the area before committing to a stay here, as there are likely better alternatives available.",
            3 to
                "My recent stay at this accommodation left me with mixed feelings. While there were aspects that I " +
                "appreciated, there were also elements that fell short of my expectations, resulting in a rather" +
                " neutral overall impression.\n" +
                "\n" +
                "Starting with the positives, the location of the accommodation was convenient and easily" +
                " accessible. Situated in a bustling area, it offered proximity to various attractions" +
                " and amenities, making it a practical choice for travelers looking to explore the area.\n" +
                "\n" +
                "The property itself was decent, albeit somewhat lacking in character. The rooms were clean " +
                "and adequately furnished, providing a comfortable space to unwind after a day of sightseeing." +
                " However, I couldn't shake the feeling that something was missing – perhaps a personal touch " +
                "or unique charm that would have elevated the experience.\n" +
                "\n" +
                "In terms of amenities, the accommodation offered the basics necessary for a comfortable stay. The " +
                "Wi-Fi was reliable, and the kitchenette was equipped with essential appliances for light meal " +
                "preparation. However, I couldn't help but wish for a few additional amenities to enhance the" +
                " overall experience.\n" +
                "\n" +
                "One area where the accommodation fell short was in terms of customer service. While the staff were " +
                "polite and professional, there was a lack of warmth and attentiveness that left me feeling" +
                " somewhat underwhelmed. It felt as though they were simply going through the motions rather " +
                "than making an effort to ensure that guests felt welcomed and valued.\n" +
                "\n" +
                "In conclusion, my stay at this accommodation was neither exceptional nor disappointing. " +
                "It provided a satisfactory base for exploring the area, but it lacked the standout" +
                " qualities that would make me eager to return. While it may suffice for travelers " +
                "seeking a no-frills option, those looking for a more memorable experience may want " +
                "to consider other options.",
            4 to
                "My recent stay at this accommodation exceeded my expectations, offering a pleasant and memorable " +
                "experience that I thoroughly enjoyed.\n" +
                "\n" +
                "First and foremost, the location of the accommodation was excellent. Situated in a charming area " +
                "with picturesque surroundings, it provided a tranquil retreat from the hustle and bustle of" +
                " daily life. Whether I was lounging on the balcony enjoying the view or exploring the nearby " +
                "attractions, every moment spent at the property was a delight.\n" +
                "\n" +
                "The accommodation itself was well-appointed and comfortable. The room was clean and spacious, with" +
                " modern furnishings and thoughtful touches that added to the overall ambiance. It was evident " +
                "that care had been taken to ensure that guests felt welcome and at home during their stay.\n" +
                "\n" +
                "In terms of amenities, the accommodation left little to be desired. The Wi-Fi was fast and " +
                "reliable, making it easy to stay connected, and the kitchen was equipped with everything" +
                " needed to prepare meals. Additionally, the bedding was comfortable, ensuring a restful" +
                " night's sleep after a day of exploration.\n" +
                "\n" +
                "One aspect that stood out during my stay was the exceptional customer service. The staff were" +
                " friendly, attentive, and went out of their way to ensure that I had a comfortable and " +
                "enjoyable stay. From providing recommendations for local attractions to addressing any " +
                "concerns I had, their hospitality was truly commendable.\n" +
                "\n" +
                "In conclusion, my experience at this accommodation was overwhelmingly positive. It offered " +
                "everything I could have asked for in a vacation rental, from a great location to comfortable " +
                "accommodations and excellent service. While there may be minor areas for improvement, " +
                "overall, I would highly recommend this property to anyone looking for a memorable getaway.",
            5 to
                "From the moment I arrived at this idyllic retreat, I was swept away by its charm and beauty." +
                " Every aspect of my stay exceeded my expectations, leaving me absolutely delighted with" +
                " my choice of accommodation.\n" +
                "\n" +
                "First and foremost, the location was nothing short of breathtaking. Nestled amidst serene " +
                "surroundings, it offered a tranquil escape from the hustle and bustle of everyday life. " +
                "Whether I was lounging on the sun-drenched terrace or exploring the nearby trails," +
                " every moment spent outdoors was sheer bliss.\n" +
                "\n" +
                "The property itself was a masterpiece of design and comfort. From the cozy interior" +
                " spaces adorned with tasteful decor to the well-appointed amenities that catered to " +
                "every need, it was evident that careful attention had been paid to every detail." +
                " The bed was sumptuously comfortable, ensuring a restful night's sleep, and the" +
                " kitchen was fully equipped with everything needed to whip up delicious meals.\n" +
                "\n" +
                "But what truly set this retreat apart was the impeccable service provided by the staff." +
                " From the warm welcome upon arrival to the attentive assistance throughout my stay," +
                " their hospitality was second to none. They went above and beyond to ensure that I" +
                " felt welcomed and cared for, making me feel like a valued guest from start to finish.\n" +
                "\n" +
                "In conclusion, my experience at this retreat was nothing short of exceptional. It " +
                "exceeded my every expectation, leaving me with cherished memories that I will" +
                " treasure for years to come. If you're looking for the perfect getaway to relax " +
                "and recharge, look no further – this is the place for you.",
        )

    fun addUserImages() {
        val users = userAccountRepository.findAll()
        val classLoader = javaClass.classLoader
        val folder = classLoader.getResource("assets/userImages")?.file
        if (folder != null) {
            val files = File(folder).listFiles() ?: throw RuntimeException("No files found in the folder")
            users.forEach { user ->
                val randomFile = files.random()
                val userImage =
                    UserImage(
                        id = 0,
                        user = user,
                        type = randomFile.extension,
                        image = randomFile.readBytes(),
                    )
                userImageRepository.save(userImage)
            }
        }
    }

    private fun getNextOrder(id: Long): Int {
        val propertyImages = this.propertyImagesRepository.findAllByPropertyId(id)
        return if (propertyImages.isEmpty()) 0 else propertyImages.maxBy { it.order }.order + 1
    }

    fun addPropertyImages() {
        val properties = this.propertyRepository.findAll()

        val classLoader = javaClass.classLoader
        val folder = classLoader.getResource("assets/propertyImages")?.file
        if (folder != null) {
            val files = File(folder).listFiles() ?: throw RuntimeException("No files found in the folder")
            properties.forEach { property ->

                for (i in 0 until 5 + Math.round(Math.random() * 3)) {
                    val file = files.random()
                    val propertyImages =
                        PropertyImages(
                            id = 0,
                            property = property,
                            order = getNextOrder(property.id),
                            image = file.readBytes(),
                            type = "image/jpeg",
                        )
                    this.propertyImagesRepository.save(propertyImages)
                }
            }
        }
    }

    fun addDummyBookings() {
        val properties = this.propertyRepository.findAll()
        val users = this.userAccountRepository.findAll()
        val random = java.util.Random()
        for (i in 0 until 100) {
            val property = properties.random()
            val user = users.random()

            if (property.host.id == user.id) continue

            val availability = this.propertyAvailabilityRepository.findAllForProperty(property).random()

            val checkIn = availability.startDate
            val checkOut = availability.startDate.plusDays(random.nextLong(10) + 1)

            if (checkOut > availability.endDate) continue

            this.propertyService.bookProperty(
                offerRequest =
                    OfferRequest(
                        checkInDate = checkIn,
                        checkOutDate = checkOut,
                        numberOfAdults = random.nextInt(property.guests) + 1,
                        numberOfChildren = 0,
                        numberOfPets = 0,
                    ),
                property = property,
                guest = user,
                this.bookingStatusRepository.findById(BookingStatusEnum.APPROVED.ordinal.toLong())
                    ?: throw BookingStatusNotFoundException("Booking status not found"),
            )
        }
    }

    fun skewedRandom(): Int {
        // Define the probabilities for each outcome (skewed right)
        val probabilities = listOf(0.05, 0.1, 0.15, 0.4, 0.3)
        // Define the cumulative probabilities
        val cumulativeProbabilities = probabilities.scan(0.0) { acc, p -> acc + p }.drop(1)
        // Generate a random number between 0 and 1
        val randomValue = Random.nextDouble()
        // Find the first cumulative probability that is greater than the random value
        for ((index, cumulativeProbability) in cumulativeProbabilities.withIndex()) {
            if (randomValue <= cumulativeProbability) {
                return index + 1
            }
        }
        return 5 // Fallback, should never reach here due to probability design
    }

    fun addDummyReviews() {
        val bookings = this.bookingRepository.findAll()

        bookings.forEach { booking ->
            val ratings =
                this.reviewComponentRepository.findAll().map {
                    ComponentRatingRequest(
                        rating = skewedRandom(),
                        reviewComponentId = it.id,
                        reviewComponentName = it.rcComponentName,
                    )
                }
            this.reviewService.createUserReview(
                review =
                    ReviewRequest(
                        comment = this.comments[Math.round(ratings.map { it.rating }.average().toFloat())] ?: "",
                        propertyId = booking.property.id,
                        bookingId = booking.id,
                        componentRatings = ratings,
                    ),
                guest = booking.guest,
                property = booking.property,
                booking = booking,
            )
        }
    }

    fun encodePasswordsForDummyUsers() {
        this.userAccountRepository.findAll().forEach { user ->
            user.userPassword = encoder().encode(user.userPassword)
            this.userAccountRepository.save(user)
        }
    }
}
