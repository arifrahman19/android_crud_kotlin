package com.example.arifrahman.crudkotlin

class ApiEndPoint {
    companion object {
        private val SERVER = "http://10.212.121.39/crudkotlinapi/"
        val CREATE = SERVER+"create.php"
        val READ = SERVER+"read.php"
        val DELETE = SERVER+"delete.php"
        val UPDATE = SERVER+"update.php"
    }
}