package CDLibrary;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class CD {

    private String title;
    private String artist;
    private List<Track> tracks;
    private int releaseYear;

    public CD(String title, String artist, List<Track> tracks, int releaseYear) {
        this.title = title;
        this.artist = artist;
        this.tracks = tracks;
        this.releaseYear = releaseYear;
    }

    //czas łączny - wyliczany przez metodę -- getTotalTime
    public int getTotalTime() {
        /*int totalTime = 0;
        for (Track track : tracks) {
            totalTime = totalTime + track.getLength();
        }*/
        //tracks.stream().reduce(0, );
        return tracks.stream()
                .mapToInt(track -> track.getLength())
                .sum();
        //return totalTime;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public Set<Genre> getGenres() {
        /*Set<Genre> genres = new HashSet<>();
        for (Track track : tracks) {
            genres.add(track.getGenre());
        }
        return genres;*/
        return tracks.stream()
                .map(track -> track.getGenre())
                .collect(Collectors.toSet());
    }

    public List<Track> findTracksByTitle(String titleFragment) {
        String lowerCaseTitleFragment = titleFragment.toLowerCase();
        return tracks.stream()
                .filter(track -> track.getTitle().toLowerCase().contains(lowerCaseTitleFragment))
                .collect(Collectors.toList());
    }

    public List<Track> findTracksByGenre(Genre genre) {
        return tracks.stream()
                .filter(track -> track.getGenre() == genre)
                .collect(Collectors.toList());
    }

}
