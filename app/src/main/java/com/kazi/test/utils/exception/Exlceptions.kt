package com.kazi.test.utils.exception

import java.io.IOException

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-04.
 */
class ApiException(message: String) : IOException(message)

class NoInternetException(message: String) : IOException(message)