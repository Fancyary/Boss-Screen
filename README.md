# Boss-Screen
A full-screen "Boss Screen" animation component built with ClojureScript + Replicant. It features golden gradient background, falling coins, avatar pop-in animation, and cycling through user messages.
It's ideal for full-screen announcements, donation displays, event popups, etc.

## How to use
The project requires Replicant. Make sure it's available in your cljs environment.

Make sure your HTML has a mount point:
```html
<div id="boss"></div>
```
`bosses` is an atom storing a vector of `[avatar-url, nickname, message, timestamp]`. Update it with your own data before running.
