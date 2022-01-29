public class Main {
    public static void main(String[] args) {
        ContactsBook book = new ContactsBook();
        book.add("374-9658020020", new Contact("Ivan", "374-9658020020"));
        book.add("977-9652223015", new Contact("Amlet", "977-9652223015"));
        book.add("7-9663332089", new Contact("Georgi", "7-9663332089"));
        book.add("867-9658021147", new Contact("Lima", "867-9658021147"));
        book.add("359-9658020000", new Contact("Artur", "359-9658020000"));
        book.add("7-9658014555", new Contact("Albert", "7-9658014555"));
        book.add("7-9658080500", new Contact("Nastasia", "7-9658080500"));

        System.out.println(book.getByPhone("359-9658020000"));
        try {
            book.removeByPhone("7-9658080500");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.println(book.contactSort());

        System.out.println(book.searchBy(contact -> contact.getName().contains("l")));
        System.out.println(book.searchByLambda("*rt"));
        System.out.println(book.getCountries());
    }
}
