package business

import ContactRepository
import entity.ContactEntity
import java.lang.Exception

class ContactBusiness {
    fun validate(name: String, phone: String) {
        if (name == "") {
            throw Exception("Nome é obrigatório!")
        }
        if (phone == "") {
            throw Exception("Telefone é obrigatório!")
        }
    }

    fun validateDelete(name: String, phone: String) {
        if (name == "" || phone == "") {
            throw Exception("É necessário selecionar um contato antes de remover")
        }
    }
    fun getContactCoutDescription(): String {
    val list = getLIst()
      return when {
            list.isEmpty() -> "0 contatos"
            list.size == 1-> "1 contato"
            else -> "${list.size} contatos"
        }
    }

    fun save(name: String, phone: String) {
        validate(name, phone)
        val contact = ContactEntity(name, phone)
        ContactRepository.save(contact)
    }

    fun delete(name: String, phone: String) {
        validateDelete(name, phone)

        val contact = ContactEntity(name, phone)
        ContactRepository.delete(contact)
    }

    fun getLIst(): List<ContactEntity> {
        return ContactRepository.getLIst()
    }
}