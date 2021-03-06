package dao;

import org.springframework.jdbc.core.JdbcTemplate;

import model.*;
import java.util.Collection;
import java.util.ArrayList;


public class TrackDAO {
    private JdbcTemplate jdbcTemplate;

    public TrackDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }


    public Track createTrack(Track track){


        this.jdbcTemplate.update("INSERT INTO tracks(title, albumid) VALUES (?,?)", track.getTitle(), track.getAlbumId());



        return track;
    }

    public Track getTrack(int id){
        Track track = new Track(id);
        //jdbcTemplate.query("SELECT title, album, id FROM tracks");

        return track;
    }

    public Collection<Track> getAllTracks(){
        Collection<Track> tracks = new ArrayList<Track>();


        jdbcTemplate.query("select * from Track", (resultSet,i) -> {
          return toTrack(resultSet);
        }

        return tracks;
    }

    public Collection<Track> getTracksByAlbumId(int albumId){
        Collection<Track> tracks = new ArrayList<Track>();

        this.jdbcTemplate.query(
                "SELECT id, title FROM tracks WHERE album = ?", new Object[] { albumId },
                (rs, rowNum) -> new Track(rs.getInt("id"), rs.getString("title"),albumId)
        ).forEach(track -> tracks.add(track) );

        return tracks;
    }
    public Track updateTrack(Track track){

        this.jdbcTemplate.update("UPDATE INTO tracks(title, albumid) VALUES (?,?)", track.getTitle(), track.getAlbumId());



        return track;
    }

    public boolean deleteTrack(Track track){
        boolean success = false;

        jdbcTemplate.update("delete from tracks where id = ?", track);
        return success;
    }

}
