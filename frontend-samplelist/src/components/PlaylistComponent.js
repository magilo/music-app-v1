import React from 'react';
import PlaylistService from '../services/PlaylistService';

class PlaylistComponent extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      currentMusicList: []
    }
  }

  //lifecycle method called immediately after component is mounted
  async componentDidMount() {
    console.log("inside componentDidMount")
    const response = await PlaylistService.getCurrentMusicList();
    // const json = await response.json();
    console.log('response', response.data);
    this.setState({ currentMusicList: response.data });
    // PlaylistService.getCurrentMusicList().then((response) => {
    //   this.setState({ currentMusicList: response.data });
    // })
  }

  render() {
    console.log("playlistComponent state", this.state)
    return (
      <div>

      </div>
    )
  }
}

export default PlaylistComponent;
