## Requirements

PR merge queue manager.
For microservice arch where we have dependency between PRs to be merged for a feature
we need a mechanism to combine then and do operations a single feature than PR's.

- As soon as a PR is raised, it gets registered with PRM under a feature.(feature name will
be branch name's first word, same across all PR's under a feature)
- Once the PR is reviewed and all build checks are passed, reviewer/author can comment on the PR
/test or /merge.
- /test will ask PRM to trigger the test for this feature using a header.
- /merge will put this PR set in a queue which PRM is working on, PRM pick the set and check all checks
and then trigger the regression and upon successful regression, merge all the PRs and then pick the next set.
- while PR's are merged, an async process will update rest of the queue PR's
- PRM will wait for the build and deployment before proceeding with the next set of PR.
- all services impl is overridable and pluggable
- all functionality can be enabled disabled.
- provision to impl and create your own chain of things to do before merge.