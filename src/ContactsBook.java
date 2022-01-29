import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ContactsBook {
    Map<String, Contact> contactList = new HashMap<>();

    public void add(String number, Contact contact) {
        contactList.put(number, contact);
    }

    public Contact getByPhone(String phone) {
        if (contactList.containsKey(phone)) {
            return contactList.get(phone);
        }
        return null;
    }

    public void removeByPhone(String phone) {
        if (contactList.containsKey(phone)) {
            contactList.remove(phone);
        } else {
            throw new IllegalArgumentException("Такого номера нет в базе: ");
        }
    }

    public List<Contact> contactSort() {
        List<Contact> list = new ArrayList<>(contactList.values());
        list.sort(new ContactsComparatorByName());
        return list;
    }

    public Set<Contact> searchBy(Predicate<Contact> contactPredicate) {
        Set<Contact> set = new TreeSet<>(new ContactsComparatorByName());
        for (Contact value : contactList.values()) {
            if (contactPredicate.test(value)) {
                set.add(value);
            }
        }
        return set;
    }

    public Set<Contact> searchByLambda(String partOfName) {
        String[] part = partOfName.replace("*", " ").split("\\s");

        return searchBy(contact -> {
            if (partOfName.startsWith("*")) {
                return contact.getName().endsWith(part[1]);
            } else if (partOfName.endsWith("*")) {
                return contact.getName().startsWith(part[0]);
            }
            return contact.getName().startsWith(part[0]) && contact.getName().endsWith(part[1]);
        });
    }

    public Set<Integer> getCountries() {
        return contactList.keySet().stream()
                .map(contact -> contact.split("-")).filter(s -> s[0].matches("\\w{0,7}."))
                .mapToInt(s -> Integer.parseInt(s[0]))
                .boxed()
                .collect(Collectors.toSet());
    }
}