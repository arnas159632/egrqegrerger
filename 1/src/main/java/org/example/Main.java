package org.example;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@interface Author {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@interface LastModified {
    String value();
}


@FunctionalInterface
interface LibraryAction<T, R> {
    R performAction(T input);
    default void a(){

    }
    static void aaa(){}

    private void b(){

    }

}

@Author("Arnas DREVINSKAS")
@LastModified("2024-04-11")
public class Main {
    private Map<Integer, Book> booksMap;
    private Set<Member> membersSet;

    public Main() {
        booksMap = new HashMap<>();
        membersSet = new HashSet<>();
    }

    public <T, R> R performLibraryAction(LibraryAction<T, R> action, T input) {
        return action.performAction(input);
    }

    public void addBook(Book book) {
        booksMap.put(book.getId(), book);
    }

    public void removeBook(int bookId) {
        if (!booksMap.containsKey(bookId)) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        booksMap.remove(bookId);
    }

    public void addMember(Member member) throws MemberLimitExceededException {
        if (membersSet.size() >= 100) {
            throw new MemberLimitExceededException("Member limit exceeded. Cannot add more members.");
        }
        membersSet.add(member);
    }

    public void removeMember(Member member) {
        membersSet.remove(member);
    }

    public List<Book> sortBooksByTitle() {
        List<Book> sortedBooks = new ArrayList<>(booksMap.values());
        sortedBooks.sort(Comparator.comparing(Book::getTitle));
        return sortedBooks;
    }

    public List<Book> sortBooksByYear() {
        List<Book> sortedBooks = new ArrayList<>(booksMap.values());
        sortedBooks.sort(Book.yearComparator);
        return sortedBooks;
    }

    public List<Member> sortMembersByName() {
        List<Member> sortedMembers = new ArrayList<>(membersSet);
        Collections.sort(sortedMembers);
        return sortedMembers;
    }

    public List<Member> sortMembersByMembershipType() {
        List<Member> sortedMembers = new ArrayList<>(membersSet);
        sortedMembers.sort(Comparator.comparing(Member::getMembershipType));
        return sortedMembers;
    }


    public List<Member> filterMembersByMembershipType(String membershipType) {
        List<Member> filteredMembers = new ArrayList<>();
        for (Member member : membersSet) {
            if (member.getMembershipType().equals(membershipType)) {
                filteredMembers.add(member);
            }
        }
        return filteredMembers;
    }

    public List<Book> searchBooksByAuthor(String authorName) {
        return performLibraryAction(
                books -> {
                    List<Book> result = new ArrayList<>();
                    for (Book book : books) {
                        if (book.getAuthor().equalsIgnoreCase(authorName)) {
                            result.add(book);
                        }
                    }
                    return result;
                },
                new ArrayList<>(booksMap.values())
        );
    }

    public void updateBookDetails(int bookId, LibraryAction<Book, Void> updateAction) {
        Book bookToUpdate = booksMap.get(bookId);
        if (bookToUpdate != null) {
            performLibraryAction(updateAction, bookToUpdate);
        } else {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
    }

    public static void main(String[] args) {
        Main library = new Main();

        List<Book> booksByAuthor = library.searchBooksByAuthor("Arnas DREVINSKAS");
        System.out.println("Books by Alice Smith:");
        for (Book book : booksByAuthor) {
            System.out.println(book);
        }

        library.addBook(new Book(1, "Introduction to Java", "John Doe", 2019));
        library.addBook(new Book(2, "Data Structures and Algorithms", "Alice Smith", 2021));
        library.addBook(new Book(3, "Advanced Java", "Alice Smith", 2022));
        library.addBook(new Book(4, "Python Programming", "Bob Johnson", 2020));

        library.removeBook(1);

        library.updateBookDetails(3, book -> {
            book.setTitle("New Title");
            book.setAuthor("New Author");
            book.setYear(2025);
            return null;
        });

        System.out.println("\nBooks in the library after update:");
        for (Book book : library.booksMap.values()) {
            System.out.println(book);
        }

        try {
            getID(2);
        } catch (MemberLimitExceededException e) {
            throw new RuntimeException(e);
        }



        int id = 0;

        try{
            id = 10/0;
        }catch (ArithmeticException e){

        }


            getName("John Doe");


    }


    public static void getID(int id) throws MemberLimitExceededException{
        if(id == 0){
            throw new MemberLimitExceededException("Member limit exceeded. Cannot add more members.");
        }
    }

    public static void getName(String name) {
        if(name == null){
            throw new BookNotFoundException("Book with ID " + name + " not found.");
        }
    }



}

