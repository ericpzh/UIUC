# MP 0: The Basics
### Due: Monday, Feb 04, 11:59PM

## Table of Contents
1. [Assignment](#assignment)
2. [Grading Breakdown](#grading-breakdown)
3. [Rules](#rules)
4. [Environment Setup Guide](#environment-setup-guide)
5. [Submission Details](#submission-details)

## Assignment
This programming assignment has two parts.

### Part A

#### Task
You will design a simple card using only HTML/CSS. The card will look like [this example](http://i.imgur.com/aeKrEga.png) except with your own details.
You will be using [Codepen](https://codepen.io/) for this part. You will have to create a free account on Codepen and then create a new pen. Write all separate parts in the right sections.

#### Requirements
1. The webpage should mirror the layout shown in the example.
2. Elements: You should use at least one of all the following: image, link, paragraph, heading.
3. Layout: The image, heading, and link must all be horizontally centered. The biography text can be either left-aligned or horizontally centered.
4. Styling: All styling must lie within the CSS section
5. The image should have a height and width of 150px.

#### Optional
If you'd like to get some hands-on experience with Javascript before the next MP, you may attempt to recreate [this example](https://uiuc-web-programming.github.io/sp19/images/mp0.gif). Although this will not be graded, understanding this early-on will make your life significantly easier for the next MP! :)

### Part B

#### Task
You will be setting up your development environment as detailed in [Part 4](#environment-setup-guide) of this documentation, and making a few small changes to the page's styling.

#### Requirements
1. Get MP0 starter to run
2. Add CSS inside the `src/css` folder so that `Hello World!` is centered
3. Add CSS rules to change the background color of the page

## Grading Breakdown

### Part A: 3%
1. Correct HTML tags and content: 50%
2. Correct CSS styling: 50%

### Part B: 2%
1. Centering heading text: 50%
2. Changing background color: 50%

## Rules
1. This is an individual assignment. No collaboration is permitted.
2. It is not permitted to copy/paste code that is not your own. You are, however, free to look at different code sources for inspiration and clarity. All sources (code as well as reading material) that you reference to complete this assignment must be declared in the submission.
3. There must be no use of any library.
4. There should be no use of inline styling.
5. No inline script tags should be used.
6. HTML tables cannot be used for layout.
7. If you think something you’re doing might not be acceptable, please ask on Piazza.

## Environment Setup Guide
0. Install [Node.js LTS](https://nodejs.org/en/) and [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
1. Clone the repository:
`git clone https://github.com/uiuc-web-programming/mp0_starter.git mp0`, then `cd mp0`
2. Install dependencies:
`npm install`
3. Run the dev server:
`npm run dev`
4. Open a browser and go to `http://localhost:8080/` to view your page. Note that if for some reason your port 8080 is occupied, it will default to 8081.

## Submission Details
Here's what you will need to submit:

### Part A:
- Save your pen and get the url to your pen

### Part B:
1. Create a private repository on GitHub. Make sure "Initialize this repository with a README" is not checked.
2. Change the remote url for the mp0 directory to the url of the new private repository you created. [Guide](https://help.github.com/articles/changing-a-remote-s-url/)
3. Push your local changes to the new repository. (`git push -u origin master`)
4. Add "UIUCWP-Bot" as a collaborator to your private repository. [Guide](https://help.github.com/articles/inviting-collaborators-to-a-personal-repository/)
5. Run `npm run deploy` to deploy your application using GitHub Pages.
6. After deploy finishes; you should see your deployed application on `<username>.github.io/<repo>`.

To finish your submission, fill out the form [here](https://uiucwp.typeform.com/to/xbx0Im).
