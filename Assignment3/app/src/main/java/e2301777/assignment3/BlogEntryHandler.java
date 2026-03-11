package e2301777.assignment3;

import java.util.ArrayList;
import java.util.List;
public class BlogEntryHandler {
    private static List<BlogEntry> blogEntries = new ArrayList<>();

    public void addBlogEntry(BlogEntry blogEntry){
        blogEntries.add(0, blogEntry);
    }

    public List<BlogEntry> getAllEntries(){
        return blogEntries;
    }

    public List<BlogEntry> filterByText(String filterText) {
        List<BlogEntry> matches = new ArrayList<>();
        for (BlogEntry entry : blogEntries) {
            if (entry.searchByText(filterText)) {
                matches.add(entry);
            }
        }
        return matches;
    }

    public List<BlogEntry> filterByDate(String filterDate) {
        List<BlogEntry> foundMatches = new ArrayList<>();
        for (BlogEntry entry : blogEntries) {
            if (entry.searchByDate(filterDate)) {
                foundMatches.add(entry);
            }
        }
        return foundMatches;
    }
}
