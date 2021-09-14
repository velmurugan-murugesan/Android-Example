package com.example.navigationrailexample

class Mail() {

    var from: String? = null;
    var subject: String? = null;


    constructor(from: String, subject: String) : this() {
        this.from = from
        this.subject = subject
    }

    fun getInboxMailList() : List<Mail> {
        var mailList = mutableListOf<Mail>()
        mailList.add(Mail("Airtel Broadband","Enjoy unlimited data from airtel. Free router with First month Free"))
        mailList.add(Mail("Jio Internet","Enjoy unlimited data from airtel. Free router with First month Free"))
        mailList.add(Mail("BSNL Network","Enjoy unlimited data from airtel. Free router with First month Free"))
        mailList.add(Mail("Hathway Broadband","Enjoy unlimited data from airtel. Free router with First month Free"))
        mailList.add(Mail("Jio Connect","Enjoy unlimited data from airtel. Free router with First month Free"))
        return mailList
    }

}