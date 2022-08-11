package servicecordinator.retrofit.model.inter

import retrofit2.Response

interface XTValidationCode<in T> {
    fun executeValidation(response: T){

    }
}