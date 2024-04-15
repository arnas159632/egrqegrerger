package org.example;

@Author("Arnas DREVINSKAS")
@LastModified("2024-04-11")
public class Member implements Comparable<Member> {
    private int id;
    private String name;
    private String membershipType;

    public Member(int id, String name, String membershipType) {
        this.id = id;
        this.name = name;
        this.membershipType = membershipType;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    @Override
    public int compareTo(Member otherMember) {
        return this.name.compareTo(otherMember.name);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", membershipType='" + membershipType + '\'' +
                '}';
    }
}
