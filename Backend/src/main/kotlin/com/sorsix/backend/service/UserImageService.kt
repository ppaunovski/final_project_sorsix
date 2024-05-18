package com.sorsix.backend.service

import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.domain.entities.UserImage
import com.sorsix.backend.repository.user_image_repository.UserImageRepository
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.net.URL

@Service
class UserImageService(private val userImageRepository: UserImageRepository){

    fun saveImageFromUrl(imageUrl: String, user: UserAccount):UserImage {
        val url = URL(imageUrl)
        val connection = url.openConnection()
        connection.connect()

        // Convert the image to a byte array
        val inputStream = connection.getInputStream()
        val outputStream = ByteArrayOutputStream()
        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } != -1) {
            outputStream.write(buffer, 0, length)
        }
        val byteArray = outputStream.toByteArray()

        // Determine the file type
        val contentType = connection.contentType
        val type = contentType.substringAfterLast('/')

        // Close streams
        inputStream.close()
        outputStream.close()


        val uI =  UserImage(
            id = 0,
            user = user,
            type = type,
            image = byteArray
        )
        return userImageRepository.save(uI)
    }
}